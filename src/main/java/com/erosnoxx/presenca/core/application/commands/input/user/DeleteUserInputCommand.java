package com.erosnoxx.presenca.core.application.commands.input.user;

import java.util.UUID;

public record DeleteUserInputCommand(UUID id) {
    public static DeleteUserInputCommand of(UUID id) {
        return new DeleteUserInputCommand(id);
    }
}
