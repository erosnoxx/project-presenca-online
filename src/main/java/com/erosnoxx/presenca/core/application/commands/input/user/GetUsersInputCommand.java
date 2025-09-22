package com.erosnoxx.presenca.core.application.commands.input.user;


import org.springframework.data.domain.Pageable;

public record GetUsersInputCommand(Pageable pageable) {
    public static GetUsersInputCommand of(Pageable pageable) {
        return new GetUsersInputCommand(pageable);
    }
}
