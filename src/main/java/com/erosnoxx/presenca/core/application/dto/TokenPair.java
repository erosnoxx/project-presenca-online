package com.erosnoxx.presenca.core.application.dto;

import com.erosnoxx.presenca.core.application.commands.output.LoginOutputCommand;

import java.time.ZonedDateTime;

public record TokenPair(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
    public LoginOutputCommand toOutput() {
        return new LoginOutputCommand(accessToken, refreshToken, expiresAt);
    }
}
