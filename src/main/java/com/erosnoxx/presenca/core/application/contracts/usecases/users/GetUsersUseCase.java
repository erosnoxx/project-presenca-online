package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUsersInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUsersOutputCommand;

public interface GetUsersUseCase {
    GetUsersOutputCommand execute(GetUsersInputCommand input);
}
