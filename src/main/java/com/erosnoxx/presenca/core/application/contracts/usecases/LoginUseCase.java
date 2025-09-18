package com.erosnoxx.presenca.core.application.contracts.usecases;

import com.erosnoxx.presenca.core.application.commands.input.LoginInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.LoginOutputCommand;

public interface LoginUseCase {
    LoginOutputCommand execute(LoginInputCommand input);
}
