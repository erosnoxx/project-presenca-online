package com.erosnoxx.presenca.core.application.commands.input.student;

import java.time.LocalDate;
import java.util.UUID;

public record CalculateAttendancePercentageInputCommand(
        UUID studentId,
        LocalDate start,
        LocalDate end) {
    public static CalculateAttendancePercentageInputCommand of(
            UUID studentId,
            LocalDate start,
            LocalDate end) {
        return new CalculateAttendancePercentageInputCommand(studentId, start, end);
    }
}
