package com.erosnoxx.presenca.core.application.commands.input.user;

import java.util.UUID;

public record GetUserByIdInputCommand(UUID id) {
    public static GetUserByIdInputCommand of(UUID id) {
        return new GetUserByIdInputCommand(id);
    }
}
