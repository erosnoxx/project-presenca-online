package com.erosnoxx.presenca.core.application.commands.input.student;

import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;

import java.time.LocalDate;
import java.util.UUID;

public record RecordAttendanceInputCommand(
        UUID studentId,
        boolean isPresent,
        AbsenceReason reason,
        LocalDate date) {
}
