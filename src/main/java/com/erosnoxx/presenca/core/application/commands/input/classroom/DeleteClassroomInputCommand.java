package com.erosnoxx.presenca.core.application.commands.input.classroom;

import java.util.UUID;

public record DeleteClassroomInputCommand(UUID id) {
    public static DeleteClassroomInputCommand of(UUID id) {
        return new DeleteClassroomInputCommand(id);
    }
}
