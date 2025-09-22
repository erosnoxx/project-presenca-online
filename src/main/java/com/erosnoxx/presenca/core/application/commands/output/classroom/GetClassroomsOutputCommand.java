package com.erosnoxx.presenca.core.application.commands.output.classroom;

import com.erosnoxx.presenca.core.application.dto.entities.ClassroomDto;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import org.springframework.data.domain.Page;


public record GetClassroomsOutputCommand(Page<ClassroomDto> classrooms) {
    public static GetClassroomsOutputCommand of(Page<Classroom> classrooms) {
        return new GetClassroomsOutputCommand(classrooms.map(ClassroomDto::of));
    }
}
