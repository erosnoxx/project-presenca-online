package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.UpdateUserInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

public interface UpdateUserUseCase {
    UUIDOutputCommand execute(UpdateUserInputCommand input);
}
