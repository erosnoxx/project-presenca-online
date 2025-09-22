package com.erosnoxx.presenca.core.application.commands.output.classroom;

import com.erosnoxx.presenca.core.application.dto.entities.ClassroomDto;
import com.erosnoxx.presenca.core.application.dto.entities.UserDto;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.entities.User;

public record GetClassroomByIdOutputCommand(ClassroomDto classroom) {
    public static GetClassroomByIdOutputCommand of(Classroom classroom) {
        return new GetClassroomByIdOutputCommand(ClassroomDto.of(classroom));
    }
}
