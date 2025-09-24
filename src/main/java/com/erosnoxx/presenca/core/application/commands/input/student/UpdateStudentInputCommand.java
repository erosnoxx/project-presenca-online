package com.erosnoxx.presenca.core.application.commands.input.student;

import java.util.Optional;
import java.util.UUID;

public record UpdateStudentInputCommand(
        UUID id,
        Optional<String> name,
        Optional<String> registrationNumber,
        Optional<UUID> classroomId) {
    public static UpdateStudentInputCommand of(
            UUID id,
            Optional<String> name,
            Optional<String> registrationNumber,
            Optional<UUID> classroomId) {
        return new UpdateStudentInputCommand(id, name, registrationNumber, classroomId);
    }
}
