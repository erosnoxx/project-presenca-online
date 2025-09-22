package com.erosnoxx.presenca.core.application.commands.input.classroom;

import java.util.UUID;

public record GetClassroomByIdInputCommand(UUID id) {
    public static GetClassroomByIdInputCommand of(UUID id) {
        return new GetClassroomByIdInputCommand(id);
    }
}
