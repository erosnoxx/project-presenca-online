package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUsersInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUsersOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.GetUsersUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository repository;

    public GetUsersUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetUsersOutputCommand execute(GetUsersInputCommand input) {
        return GetUsersOutputCommand.of(repository.findAll(input.pageable()));
    }
}
