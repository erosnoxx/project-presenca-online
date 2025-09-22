package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByIdInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomByIdOutputCommand;

public interface GetClassroomByIdUseCase {
    GetClassroomByIdOutputCommand execute(GetClassroomByIdInputCommand input);
}
