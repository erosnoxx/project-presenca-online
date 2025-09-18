package com.erosnoxx.presenca.core.application.usecases;

import com.erosnoxx.presenca.core.application.commands.input.LoginInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.LoginOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.services.AuthenticatorService;
import com.erosnoxx.presenca.core.application.contracts.usecases.LoginUseCase;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    private final AuthenticatorService authenticatorService;

    public LoginUseCaseImpl(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    @Override
    public LoginOutputCommand execute(LoginInputCommand input) {
        return authenticatorService
                .validateCredentials(input.username(), input.password())
                .toOutput();
    }
}
