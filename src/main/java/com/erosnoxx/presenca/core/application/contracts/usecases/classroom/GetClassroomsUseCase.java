package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomsInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomsOutputCommand;

public interface GetClassroomsUseCase {
    GetClassroomsOutputCommand execute(GetClassroomsInputCommand input);
}
