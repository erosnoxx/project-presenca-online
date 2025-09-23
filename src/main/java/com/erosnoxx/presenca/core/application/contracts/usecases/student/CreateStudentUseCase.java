package com.erosnoxx.presenca.core.application.contracts.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.CreateStudentInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface CreateStudentUseCase {
    UUIDOutputCommand execute(CreateStudentInputCommand input);
}
