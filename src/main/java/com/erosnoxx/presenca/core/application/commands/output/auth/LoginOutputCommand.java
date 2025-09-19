package com.erosnoxx.presenca.core.application.commands.output.auth;

import java.time.ZonedDateTime;

public record LoginOutputCommand(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
}
