package com.erosnoxx.presenca.api.schemas.request.user;

import com.erosnoxx.presenca.core.application.commands.input.user.CreateUserInputCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(
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
    public CreateUserInputCommand toInput() {
        return new CreateUserInputCommand(username, password);
    }
}
