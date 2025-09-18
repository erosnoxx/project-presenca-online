package com.erosnoxx.presenca.api.schemas.request;

import com.erosnoxx.presenca.core.application.commands.input.LoginInputCommand;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
        @NotBlank @Length(min = 3, max = 20) String username,
        @NotBlank @Length(min = 8, max = 100) String password
) {
    public LoginInputCommand toInput() {
        return new LoginInputCommand(username, password);
    }
}
