package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.DeleteUserInputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.DeleteUserUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository repository;

    public DeleteUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteUserInputCommand input) {
        repository.delete(repository.findById(input.id())
                .orElseThrow(() -> new UserNotFoundException(
                        "user not found with id: " + input.id())));
    }
}
