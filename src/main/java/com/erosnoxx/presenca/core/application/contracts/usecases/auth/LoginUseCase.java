package com.erosnoxx.presenca.core.application.contracts.usecases.auth;

import com.erosnoxx.presenca.core.application.commands.input.auth.LoginInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.auth.LoginOutputCommand;

public interface LoginUseCase {
    LoginOutputCommand execute(LoginInputCommand input);
}
