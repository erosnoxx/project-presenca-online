package com.erosnoxx.presenca.core.application.commands.output;

import java.time.ZonedDateTime;

public record LoginOutputCommand(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
}
