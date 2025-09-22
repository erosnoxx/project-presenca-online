package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.CreateClassroomInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.CreateClassroomUseCase;
import com.erosnoxx.presenca.core.domain.checkers.ClassroomChecker;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class CreateClassroomUseCaseImpl implements CreateClassroomUseCase {
    private final ClassroomRepository repository;
    private final ClassroomChecker checker;

    public CreateClassroomUseCaseImpl(
            ClassroomRepository repository,
            ClassroomChecker checker) {
        this.repository = repository;
        this.checker = checker;
    }

    @Override
    public UUIDOutputCommand execute(CreateClassroomInputCommand input) {
        var classroom = repository.save(
                Classroom.create(input.className(), checker));

        return UUIDOutputCommand.of(classroom);
    }
}
