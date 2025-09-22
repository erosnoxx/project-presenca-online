package com.erosnoxx.presenca.core.application.usecases.classroom;

import com.erosnoxx.presenca.core.application.commands.input.classroom.DeleteClassroomInputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.DeleteClassroomUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteClassroomUseCaseImpl implements DeleteClassroomUseCase {
    private final ClassroomRepository repository;

    public DeleteClassroomUseCaseImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteClassroomInputCommand input) {
        repository.delete(
                repository.findById(input.id())
                        .orElseThrow(() -> new ClassroomNotFoundException(
                                "user not found with id: " + input.id())));
    }
}
