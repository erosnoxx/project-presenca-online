package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.CreateClassroomInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface CreateClassroomUseCase {
    UUIDOutputCommand execute(CreateClassroomInputCommand input);
}
