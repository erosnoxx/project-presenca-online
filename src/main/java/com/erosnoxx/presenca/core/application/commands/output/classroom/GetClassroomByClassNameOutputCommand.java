package com.erosnoxx.presenca.core.application.commands.output.classroom;

import com.erosnoxx.presenca.core.application.dto.entities.ClassroomDto;
import com.erosnoxx.presenca.core.application.dto.entities.UserDto;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.entities.User;

public record GetClassroomByClassNameOutputCommand(ClassroomDto classroom) {
    public static GetClassroomByClassNameOutputCommand of(Classroom classroom) {
        return new GetClassroomByClassNameOutputCommand(ClassroomDto.of(classroom));
    }
}
