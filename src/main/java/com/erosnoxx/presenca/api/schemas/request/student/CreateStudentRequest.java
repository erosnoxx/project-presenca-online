package com.erosnoxx.presenca.api.schemas.request.student;

import com.erosnoxx.presenca.core.application.commands.input.student.CreateStudentInputCommand;

import java.util.UUID;

public record CreateStudentRequest(
        String name,
        String registrationNumber,
        UUID classroomId) {
    public CreateStudentInputCommand toInput() {
        return new CreateStudentInputCommand(name, registrationNumber, classroomId);
    }
}
