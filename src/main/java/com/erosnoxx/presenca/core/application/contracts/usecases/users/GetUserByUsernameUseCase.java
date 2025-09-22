package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.GetUserByUsernameInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.user.GetUserByUsernameOutputCommand;

public interface GetUserByUsernameUseCase {
    GetUserByUsernameOutputCommand execute(GetUserByUsernameInputCommand input);
}
