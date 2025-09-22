package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByClassNameInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomByClassNameOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomByClassNameUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetClassroomByClassNameUseCaseImpl implements GetClassroomByClassNameUseCase {
    private final ClassroomRepository repository;

    public GetClassroomByClassNameUseCaseImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetClassroomByClassNameOutputCommand execute(GetClassroomByClassNameInputCommand input) {
        return GetClassroomByClassNameOutputCommand.of(
                repository.findByClassName(input.className())
                        .orElseThrow(() -> new ClassroomNotFoundException(
                                "classroom not found with class name: " + input.className()
                        )));
    }
}
