package com.erosnoxx.presenca.core.application.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.RecordAttendanceInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.RecordAttendanceUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RecordAttendanceUseCaseImpl implements RecordAttendanceUseCase {
    private final StudentRepository repository;

    public RecordAttendanceUseCaseImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUIDOutputCommand execute(RecordAttendanceInputCommand input) {
        var student = repository.findById(input.studentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        "user not found with id: " + input.studentId()));

        student.recordAttendance(input.date(), input.isPresent(), input.reason());

        repository.save(student);

        return UUIDOutputCommand.of(student);
    }
}
