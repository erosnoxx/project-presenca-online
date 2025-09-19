package com.erosnoxx.presenca.api.schemas.request;

import com.erosnoxx.presenca.core.application.commands.input.LoginInputCommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
        @NotBlank @Length(min = 3, max = 20)
        @Pattern(
                regexp = "^[a-z]+\\.[a-z]+$",
                message = "username must be in format 'name.lastname' with only letters")
        String username,
        @NotBlank @Length(min = 8, max = 100)
        @Pattern(
                regexp = "^(?=.*[A-Za-z\\d])(?=.*\\W).{8,}$",
                message = "password must contain uppercase, lowercase, number and special character")
        String password
) {
    public LoginInputCommand toInput() {
        return new LoginInputCommand(username, password);
    }
}
