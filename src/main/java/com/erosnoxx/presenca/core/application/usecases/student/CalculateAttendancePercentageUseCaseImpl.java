package com.erosnoxx.presenca.core.application.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.CalculateAttendancePercentageInputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.CalculateAttendancePercentageUseCase;
import com.erosnoxx.presenca.core.application.dto.entities.StudentDto;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CalculateAttendancePercentageUseCaseImpl implements CalculateAttendancePercentageUseCase {
    private final StudentRepository repository;

    public CalculateAttendancePercentageUseCaseImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentDto execute(CalculateAttendancePercentageInputCommand input) {
        var student = repository.findById(input.studentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        "user not found with id: " + input.studentId()));

        return StudentDto.of(student, input.start(), input.end());
    }
}
