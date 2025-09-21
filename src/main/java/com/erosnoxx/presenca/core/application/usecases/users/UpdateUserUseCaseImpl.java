package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.UpdateUserInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.UpdateUserUseCase;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserAlreadyExistsException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserNotFoundException;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository repository;

    public UpdateUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUIDOutputCommand execute(UpdateUserInputCommand input) {
        User user = getUserOrThrow(input.id());

        updateUsernameIfPresent(user, input);
        updatePasswordIfPresent(user, input);

        persistIfChanged(user, input);

        return UUIDOutputCommand.of(user);
    }

    private User getUserOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "cannot update user, id not found: " + id
                ));
    }

    private void updateUsernameIfPresent(User user, UpdateUserInputCommand input) {
        input.username().ifPresent(username -> {
            ensureUsernameIsUnique(username);
            user.setUsername(Username.of(username));
        });
    }

    private void updatePasswordIfPresent(User user, UpdateUserInputCommand input) {
        input.password().ifPresent(password ->
                user.setPassword(Password.fromPlainText(password))
        );
    }

    private void ensureUsernameIsUnique(String username) {
        if (repository.existsByUsername(username)) {
            throw new UserAlreadyExistsException(
                    "username already in use: " + username
            );
        }
    }

    private void persistIfChanged(User user, UpdateUserInputCommand input) {
        if (input.username().isPresent() || input.password().isPresent()) {
            repository.save(user);
        }
    }
}
