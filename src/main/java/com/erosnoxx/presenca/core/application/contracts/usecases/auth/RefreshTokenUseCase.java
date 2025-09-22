package com.erosnoxx.presenca.core.application.contracts.usecases.auth;

import com.erosnoxx.presenca.core.application.commands.input.auth.RefreshTokenInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.auth.RefreshTokenOutputCommand;

public interface RefreshTokenUseCase {
    RefreshTokenOutputCommand execute(RefreshTokenInputCommand input);
}
