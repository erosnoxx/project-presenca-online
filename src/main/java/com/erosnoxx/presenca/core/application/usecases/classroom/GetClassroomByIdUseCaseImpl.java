package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByIdInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomByIdOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomByIdUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetClassroomByIdUseCaseImpl implements GetClassroomByIdUseCase {
    private final ClassroomRepository repository;

    public GetClassroomByIdUseCaseImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetClassroomByIdOutputCommand execute(GetClassroomByIdInputCommand input) {
        var entity = repository.findById(input.id())
                .orElseThrow(() ->
                        new ClassroomNotFoundException(
                                "classroom not found with id: " + input.id()));

        return GetClassroomByIdOutputCommand.of(entity);
    }
}
