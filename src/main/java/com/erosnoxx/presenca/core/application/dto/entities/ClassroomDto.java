package com.erosnoxx.presenca.core.application.dto.entities;

import com.erosnoxx.presenca.core.domain.entities.Classroom;
import java.util.List;
import java.util.UUID;

public record ClassroomDto(
        UUID id,
        String className,
        List<StudentDto> students) {
    public static ClassroomDto of(Classroom classroom) {
        return new ClassroomDto(
                classroom.getId(),
                classroom.getClassName().getValue(),
                classroom.getStudents().stream()
                        .map(s -> StudentDto.of(s, null, null))
                        .toList());
    }
}
