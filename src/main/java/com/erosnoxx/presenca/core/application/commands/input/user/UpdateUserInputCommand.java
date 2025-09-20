package com.erosnoxx.presenca.core.application.commands.input.user;

import java.util.Optional;
import java.util.UUID;

public record UpdateUserInputCommand(
        UUID id,
        Optional<String> username,
        Optional<String> password) {
    public static UpdateUserInputCommand of(
            UUID id,
            String username,
            String password) {
        return new UpdateUserInputCommand(
                id,
                Optional.of(username),
                Optional.of(password));
    }
}
