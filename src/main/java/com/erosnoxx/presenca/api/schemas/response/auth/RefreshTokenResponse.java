package com.erosnoxx.presenca.api.schemas.response.auth;

import com.erosnoxx.presenca.core.application.commands.output.auth.RefreshTokenOutputCommand;

import java.time.ZonedDateTime;

public record RefreshTokenResponse(
                                   String accessToken,
                                   String refreshToken,
                                   ZonedDateTime expiresAt) {
    public static RefreshTokenResponse of(RefreshTokenOutputCommand output) {
        return new RefreshTokenResponse(
                output.accessToken(),
                output.refreshToken(),
                output.expiresAt());
    }
}
