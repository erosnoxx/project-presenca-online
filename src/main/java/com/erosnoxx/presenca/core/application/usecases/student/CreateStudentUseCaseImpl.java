package com.erosnoxx.presenca.core.application.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.student.CreateStudentInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.CreateStudentUseCase;
import com.erosnoxx.presenca.core.domain.checkers.StudentChecker;
import com.erosnoxx.presenca.core.domain.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
    private final StudentRepository repository;
    private final StudentChecker checker;

    public CreateStudentUseCaseImpl(
            StudentRepository repository,
            StudentChecker checker) {
        this.repository = repository;
        this.checker = checker;
    }

    @Override
    public UUIDOutputCommand execute(CreateStudentInputCommand input) {
        var student = repository.save(Student.create(
                input.name(), input.registrationNumber(), input.classroomId(), checker));

        return UUIDOutputCommand.of(student);
    }
}
