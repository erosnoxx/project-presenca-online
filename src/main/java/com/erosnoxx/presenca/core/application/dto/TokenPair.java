package com.erosnoxx.presenca.core.application.dto;

import java.time.ZonedDateTime;

public record TokenPair(
        String accessToken,
        String refreshToken,
        ZonedDateTime expiresAt) {
}
