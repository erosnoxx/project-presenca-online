package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.UpdateClassroomInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.UpdateClassroomUseCase;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserAlreadyExistsException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.user.UserNotFoundException;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateClassroomUseCaseImpl implements UpdateClassroomUseCase {
    private final ClassroomRepository repository;

    public UpdateClassroomUseCaseImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUIDOutputCommand execute(UpdateClassroomInputCommand input) {
        var classroom = getClassroomOrThrow(input.id());

        updateClassNameIfPresent(classroom, input);
        persistIfChanged(classroom, input);

        return UUIDOutputCommand.of(classroom);
    }

    private Classroom getClassroomOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "cannot update classroom, id not found: " + id
                ));
    }

    private void updateClassNameIfPresent(
            Classroom classroom,
            UpdateClassroomInputCommand input) {
        input.className().ifPresent(className -> {
            ensureClassNameIsUnique(className);
            classroom.setClassName(Name.of(className));
        });
    }

    private void ensureClassNameIsUnique(String className) {
        if (repository.existsByClassName(className)) {
            throw new UserAlreadyExistsException(
                    "className already in use: " + className
            );
        }
    }

    private void persistIfChanged(Classroom classroom, UpdateClassroomInputCommand input) {
        if (input.className().isPresent())
            repository.save(classroom);
    }
}
