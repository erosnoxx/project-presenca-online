package com.erosnoxx.presenca.api.schemas.request.student;

import com.erosnoxx.presenca.core.application.commands.input.student.RecordAttendanceInputCommand;
import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record RecordAttendanceRequest(
        @NotNull boolean isPresent,
        AbsenceReason reason,
        @NotNull LocalDate date) {
    public RecordAttendanceInputCommand toInput(UUID id) {
        return new RecordAttendanceInputCommand(
                id,
                isPresent,
                reason,
                date);
    }

    @AssertTrue(message = "reason must be null when isPresent is true, and provided when false")
    private boolean isReasonValid() {
        return (isPresent && reason == null) || (!isPresent && reason != null);
    }
}
