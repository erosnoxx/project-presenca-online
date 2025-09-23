package com.erosnoxx.presenca.core.application.dto.entities;

import com.erosnoxx.presenca.core.domain.entities.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentDto(
        UUID id,
        String name,
        String registrationNumber,
        Double attendancePercentage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static StudentDto of(
            Student student, LocalDate start, LocalDate end) {
        return new StudentDto(
                student.getId(),
                student.getName().getValue(),
                student.getRegistrationNumber(),
                student.getAttendancePercentage(start, end),
                student.getCreatedAt(),
                student.getUpdatedAt());
    }
}
