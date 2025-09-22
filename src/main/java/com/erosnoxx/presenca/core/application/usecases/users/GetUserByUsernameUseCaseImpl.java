package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUserByUsernameInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUserByUsernameOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.GetUserByUsernameUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserByUsernameUseCaseImpl implements GetUserByUsernameUseCase {
    private final UserRepository repository;

    public GetUserByUsernameUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetUserByUsernameOutputCommand execute(GetUserByUsernameInputCommand input) {
        var user = repository.findByUsername(input.username())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "user not found with username: " + input.username()));

        return GetUserByUsernameOutputCommand.of(user);
    }
}
