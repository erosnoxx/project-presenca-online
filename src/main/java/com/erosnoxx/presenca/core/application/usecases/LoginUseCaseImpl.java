package com.erosnoxx.presenca.core.application.usecases;

import com.erosnoxx.presenca.core.application.commands.input.LoginInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.LoginOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.services.AuthenticatorService;
import com.erosnoxx.presenca.core.application.contracts.usecases.LoginUseCase;
import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserNotAuthenticatedException;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    private final AuthenticatorService authenticatorService;
    private final UserRepository repository;

    public LoginUseCaseImpl(
            AuthenticatorService authenticatorService,
            UserRepository repository) {
        this.authenticatorService = authenticatorService;
        this.repository = repository;
    }

    @Override
    public LoginOutputCommand execute(LoginInputCommand input) {
        var user = repository.findByUsername(input.username())
                .orElseThrow(() -> new UserNotAuthenticatedException("invalid username or password"));

        if (!user.getPassword().matches(input.password()))
            throw new UserNotAuthenticatedException("invalid username or password");

        return authenticatorService
                .validateCredentials(input.username(), input.password())
                .toOutput();
    }
}
