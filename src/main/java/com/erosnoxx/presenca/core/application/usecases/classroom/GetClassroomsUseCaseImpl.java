package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomsInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.classroom.GetClassroomsOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomsUseCase;
import com.erosnoxx.presenca.core.application.dto.entities.ClassroomDto;
import org.springframework.stereotype.Service;

@Service
public class GetClassroomsUseCaseImpl implements GetClassroomsUseCase {
    private final ClassroomRepository repository;

    public GetClassroomsUseCaseImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetClassroomsOutputCommand execute(GetClassroomsInputCommand input) {
        return GetClassroomsOutputCommand.of(
                repository.findAll(
                        input.pageable()));
    }
}
