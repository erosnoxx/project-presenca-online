package com.erosnoxx.presenca.core.application.commands.input.student;

import java.util.UUID;

public record CreateStudentInputCommand(
        String name,
        String registrationNumber,
        UUID classroomId) {
}
