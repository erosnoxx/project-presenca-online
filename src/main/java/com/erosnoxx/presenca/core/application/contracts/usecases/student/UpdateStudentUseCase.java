package com.erosnoxx.presenca.core.application.contracts.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.UpdateStudentInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface UpdateStudentUseCase {
    UUIDOutputCommand execute(UpdateStudentInputCommand input);
}
