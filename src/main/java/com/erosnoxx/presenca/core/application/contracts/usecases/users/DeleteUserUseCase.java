package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.DeleteUserInputCommand;

public interface DeleteUserUseCase {
    void execute(DeleteUserInputCommand input);
}
