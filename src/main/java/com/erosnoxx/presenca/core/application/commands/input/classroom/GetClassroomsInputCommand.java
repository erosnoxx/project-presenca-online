package com.erosnoxx.presenca.core.application.commands.input.classroom;

import org.springframework.data.domain.Pageable;

public record GetClassroomsInputCommand(Pageable pageable) {
    public static GetClassroomsInputCommand of(Pageable pageable) {
        return new GetClassroomsInputCommand(pageable);
    }
}
