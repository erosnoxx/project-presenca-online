package com.erosnoxx.presenca.core.application.contracts.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.RecordAttendanceInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface RecordAttendanceUseCase {
    UUIDOutputCommand execute(RecordAttendanceInputCommand input);
}
