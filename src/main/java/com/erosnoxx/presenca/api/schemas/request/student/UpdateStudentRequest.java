package com.erosnoxx.presenca.api.schemas.request.student;

import com.erosnoxx.presenca.core.application.commands.input.student.UpdateStudentInputCommand;

import java.util.Optional;
import java.util.UUID;

public record UpdateStudentRequest(
        Optional<String> name,
        Optional<String> registrationNumber,
        Optional<UUID> classroomId) {
    public UpdateStudentInputCommand toInput(UUID id) {
        return new UpdateStudentInputCommand(id, name, registrationNumber, classroomId);
    }
}
