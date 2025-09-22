package com.erosnoxx.presenca.core.application.contracts.usecases.classroom;


import com.erosnoxx.presenca.core.application.commands.input.classroom.DeleteClassroomInputCommand;

public interface DeleteClassroomUseCase {
    void execute(DeleteClassroomInputCommand input);
}
