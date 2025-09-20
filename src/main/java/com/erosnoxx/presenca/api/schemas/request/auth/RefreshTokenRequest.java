package com.erosnoxx.presenca.api.schemas.request.auth;

import com.erosnoxx.presenca.core.application.commands.input.auth.RefreshTokenInputCommand;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank String refreshToken) {
    public RefreshTokenInputCommand toInput() {
        return new RefreshTokenInputCommand(refreshToken);
    }
}
