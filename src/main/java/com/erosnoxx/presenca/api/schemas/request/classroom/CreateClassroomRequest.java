package com.erosnoxx.presenca.api.schemas.request.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.CreateClassroomInputCommand;
import jakarta.validation.constraints.NotBlank;

public record CreateClassroomRequest(
        @NotBlank String className) {
    public CreateClassroomInputCommand toInput() {
        return new CreateClassroomInputCommand(className);
    }
}
