package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.UpdateClassroomInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface UpdateClassroomUseCase {
    UUIDOutputCommand execute(UpdateClassroomInputCommand input);
}
