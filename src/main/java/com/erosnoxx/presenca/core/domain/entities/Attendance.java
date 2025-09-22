package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;
import com.erosnoxx.presenca.core.domain.exceptions.entities.attendance.AttendanceConsistencyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
public class Attendance extends DomainEntity<UUID> {

    private boolean present;
    private AbsenceReason reason;
    private Student student;
    private LocalDate date;

    public Attendance(boolean present, AbsenceReason reason, Student student, LocalDate date) {
        this.present = present;
        this.reason = reason;
        this.student = student;
        this.date = date;

        validateConsistency();
    }

    public boolean isAbsent() {
        return !present;
    }

    public void justifyAbsence(AbsenceReason reason) {
        if (present) {
            throw new AttendanceConsistencyException("cannot justify absence for a present student");
        }
        this.reason = reason;
    }

    public boolean belongsToPeriod(LocalDate start, LocalDate end) {
        return (date.isEqual(start) || date.isAfter(start)) &&
                (date.isEqual(end) || date.isBefore(end));
    }

    private void validateConsistency() {
        if (present && reason != null) {
            throw new AttendanceConsistencyException("present student cannot have absence reason");
        }
        if (!present && reason == null) {
            throw new AttendanceConsistencyException("absent student must have a reason");
        }
    }
}
