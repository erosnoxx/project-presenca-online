package com.erosnoxx.presenca.core.application.contracts.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.CalculateAttendancePercentageInputCommand;
import com.erosnoxx.presenca.core.application.dto.entities.StudentDto;

public interface CalculateAttendancePercentageUseCase {
    StudentDto execute(CalculateAttendancePercentageInputCommand input);
}
