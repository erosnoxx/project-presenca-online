package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.checkers.ClassroomChecker;
import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomAlreadyExistsException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentAlreadyExistsInClassRoomException;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
public class Classroom extends DomainEntity<UUID> {

    private Name className;
    private List<Student> students = new ArrayList<>();

    public Classroom(UUID id) {
        this.setId(id);
    }

    public Classroom(Name className) {
        this.className = className;
    }

    public static Classroom create(String className, ClassroomChecker checker) {
        if (!checker.isClassNameUnique(className))
            throw new ClassroomAlreadyExistsException(
                    "classroom with name: " + className + " already exists");

        return new Classroom(Name.of(className));
    }

    public void addStudent(Student student) {
        boolean exists = students.stream()
                .anyMatch(s -> s.getId().equals(student.getId()));
        if (exists) {
            throw new StudentAlreadyExistsInClassRoomException("student already in classroom");
        }
        students.add(student);
    }

    public void removeStudent(UUID studentId) {
        students.removeIf(s -> s.getId().equals(studentId));
    }

    public double getAverageAttendancePercentage(LocalDate start, LocalDate end) {
        if (students.isEmpty()) return 0.0;

        double total = students.stream()
                .mapToDouble(s -> s.getAttendancePercentage(start, end))
                .sum();

        return total / students.size();
    }

    public List<Student> getStudentsBelowThreshold(LocalDate start, LocalDate end, double threshold) {
        return students.stream()
                .filter(s -> !s.hasMinimumAttendance(start, end, threshold))
                .toList();
    }
}
