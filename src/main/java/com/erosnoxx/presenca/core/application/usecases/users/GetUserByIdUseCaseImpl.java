package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUserByIdInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUserByIdOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.GetUserByIdUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {
    private final UserRepository repository;

    public GetUserByIdUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetUserByIdOutputCommand execute(GetUserByIdInputCommand input) {
        var user = repository.findById(input.id())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "user not found with id: " + input.id()));

        return GetUserByIdOutputCommand.of(user);
    }
}
