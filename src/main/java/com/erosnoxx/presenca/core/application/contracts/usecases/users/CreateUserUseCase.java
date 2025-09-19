package com.erosnoxx.presenca.core.application.contracts.usecases.users;

import com.erosnoxx.presenca.core.application.commands.input.user.CreateUserInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

import java.util.UUID;

public interface CreateUserUseCase {
    UUIDOutputCommand execute(CreateUserInputCommand input);
}
