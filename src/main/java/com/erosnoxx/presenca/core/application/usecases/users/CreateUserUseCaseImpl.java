package com.erosnoxx.presenca.core.application.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.CreateUserInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.CreateUserUseCase;
import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository repository;
    private final UserChecker checker;

    public CreateUserUseCaseImpl(
            UserRepository repository,
            UserChecker checker) {
        this.repository = repository;
        this.checker = checker;
    }

    @Override
    public UUIDOutputCommand execute(CreateUserInputCommand input) {
        var user = repository.save(User.create(
                input.username(),
                input.password(),
                Role.USER, checker));

        return UUIDOutputCommand.of(user);
    }
}
