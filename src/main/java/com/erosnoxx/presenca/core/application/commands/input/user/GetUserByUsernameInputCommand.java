package com.erosnoxx.presenca.core.application.commands.input.user;

public record GetUserByUsernameInputCommand(String username) {
    public static GetUserByUsernameInputCommand of(String username) {
        return new GetUserByUsernameInputCommand(username);
    }
}
