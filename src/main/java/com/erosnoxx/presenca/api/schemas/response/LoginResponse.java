package com.erosnoxx.presenca.api.schemas.response;

import com.erosnoxx.presenca.core.application.commands.output.LoginOutputCommand;

import java.time.ZonedDateTime;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
    public static LoginResponse fromOutput(LoginOutputCommand output) {
        return new LoginResponse(
                output.accessToken(),
                output.refreshToken(),
                output.expiresAt());
    }
}
