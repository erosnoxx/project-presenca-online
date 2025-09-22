package com.erosnoxx.presenca.core.application.commands.output.auth;

import com.erosnoxx.presenca.core.application.dto.auth.TokenPair;

import java.time.ZonedDateTime;

public record RefreshTokenOutputCommand(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
    public static RefreshTokenOutputCommand fromPair(TokenPair pair) {
        return new RefreshTokenOutputCommand(
                pair.accessToken(),
                pair.refreshToken(),
                pair.expiresAt());
    }
}
