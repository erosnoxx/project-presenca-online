package com.erosnoxx.presenca.api.schemas.request.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.UpdateClassroomInputCommand;

import java.util.Optional;
import java.util.UUID;

public record UpdateClassroomRequest(
        Optional<String> className) {
    public UpdateClassroomInputCommand toInput(UUID id) {
        return new UpdateClassroomInputCommand(id, className);
    }
}
