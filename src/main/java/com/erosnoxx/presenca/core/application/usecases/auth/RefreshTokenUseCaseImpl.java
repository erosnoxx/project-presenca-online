package com.erosnoxx.presenca.core.application.usecases.auth;

import com.erosnoxx.presenca.core.application.commands.input.auth.RefreshTokenInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.auth.RefreshTokenOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.services.AuthenticatorService;
import com.erosnoxx.presenca.core.application.contracts.usecases.auth.RefreshTokenUseCase;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {
    private final AuthenticatorService authenticatorService;

    public RefreshTokenUseCaseImpl(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    @Override
    public RefreshTokenOutputCommand execute(RefreshTokenInputCommand input) {
        return RefreshTokenOutputCommand.fromPair(
                authenticatorService.renewToken(
                        input.refreshToken()));
    }
}
