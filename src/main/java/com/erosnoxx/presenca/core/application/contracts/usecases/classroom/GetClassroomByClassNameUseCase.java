package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByClassNameInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomByClassNameOutputCommand;

public interface GetClassroomByClassNameUseCase {
    GetClassroomByClassNameOutputCommand execute(GetClassroomByClassNameInputCommand input);
}
