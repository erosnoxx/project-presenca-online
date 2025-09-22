package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUserByIdInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUserByIdOutputCommand;

public interface GetUserByIdUseCase {
    GetUserByIdOutputCommand execute(GetUserByIdInputCommand input);
}
