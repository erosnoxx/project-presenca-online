package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.checkers.StudentChecker;
import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;
import com.erosnoxx.presenca.core.domain.exceptions.entities.attendance.AttendanceConsistencyException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentAlreadyExistsInClassRoomException;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter @Setter @NoArgsConstructor
public class Student extends DomainEntity<UUID> {

    private Name name;
    private String registrationNumber;
    private Classroom classroom;
    private List<Attendance> attendances = new ArrayList<>();

    public Student(UUID id) {
        setId(id);
    }

    public Student(Name name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
    }

    public static Student create(String name, String registrationNumber, UUID classroomId, StudentChecker checker) {
        if (!checker.isRegistrationNumberUnique(registrationNumber))
            throw new StudentAlreadyExistsInClassRoomException("student already exists in classroom");

        var student = new Student(
                Name.of(name),
                registrationNumber);
        student.setClassroom(new Classroom(classroomId));

        return student;
    }

    public void recordAttendance(LocalDate date, boolean present, AbsenceReason reason) {
        Map<Boolean, Runnable> actions = Map.of(
                true, () -> markPresence(date),
                false, () -> markAbsence(date, reason)
        );

        actions.get(present).run();
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
        var effectiveStart = resolveStart(start);
        var effectiveEnd = resolveEnd(end);

        validatePeriod(effectiveStart, effectiveEnd);

        long total = attendances.stream()
                .filter(a -> a.belongsToPeriod(effectiveStart, effectiveEnd))
                .count();

        if (total == 0) return 0.0;

        long present = attendances.stream()
                .filter(a -> a.belongsToPeriod(effectiveStart, effectiveEnd) && a.isPresent())
                .count();

        return (present * 100.0) / total;
    }

    private LocalDate resolveStart(LocalDate start) {
        return (start == null)
                ? LocalDate.of(LocalDate.now().getYear(), 1, 1)
                : start;
    }

    private LocalDate resolveEnd(LocalDate end) {
        return (end == null)
                ? LocalDate.now()
                : end;
    }

    private void validatePeriod(LocalDate start, LocalDate end) {
        if (start.isAfter(LocalDate.now())) {
            throw new AttendanceConsistencyException("start date cannot be in the future");
        }

        if (end.isAfter(LocalDate.now())) {
            throw new AttendanceConsistencyException("end date cannot be in the future");
        }

        if (start.isAfter(end)) {
            throw new AttendanceConsistencyException("start date cannot be after end date");
        }
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
