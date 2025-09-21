package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;
import com.erosnoxx.presenca.core.domain.exceptions.entities.attendance.AttendanceConsistencyException;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
public class Student extends DomainEntity<UUID> {

    private Name name;
    private String registrationNumber;
    private Classroom classroom;
    private List<Attendance> attendances = new ArrayList<>();


    public Student(Name name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
    }

    public void markPresence(LocalDate date) {
        ensureNoDuplicate(date);

        attendances.removeIf(a -> a.getDate().equals(date) && a.isAbsent());

        attendances.add(new Attendance(true, null, this, date));
    }

    public void markAbsence(LocalDate date, AbsenceReason reason) {
        ensureNoDuplicate(date);

        var alreadyAbsent = attendances.stream()
                .anyMatch(a -> a.getDate().equals(date) && a.isAbsent());
        if (alreadyAbsent) {
            throw new AttendanceConsistencyException("absence already registered for this date");
        }

        attendances.add(new Attendance(false, reason, this, date));
    }

    public double getAttendancePercentage(LocalDate start, LocalDate end) {
        long total = attendances.stream()
                .filter(a -> a.belongsToPeriod(start, end))
                .count();

        if (total == 0) return 0.0;

        long present = attendances.stream()
                .filter(a -> a.belongsToPeriod(start, end) && a.isPresent())
                .count();

        return (present * 100.0) / total;
    }

    public boolean hasMinimumAttendance(LocalDate start, LocalDate end, double threshold) {
        return getAttendancePercentage(start, end) >= threshold;
    }

    private void ensureNoDuplicate(LocalDate date) {
        boolean exists = attendances.stream()
                .anyMatch(a -> a.getDate().equals(date) && a.isPresent());
        if (exists) {
            throw new AttendanceConsistencyException("attendance already registered for this date");
        }
    }

    public void transferTo(Classroom newClassroom) {
        if (this.classroom != null) {
            this.classroom.removeStudent(this.getId());
        }
        this.classroom = newClassroom;
        newClassroom.addStudent(this);
    }
}
