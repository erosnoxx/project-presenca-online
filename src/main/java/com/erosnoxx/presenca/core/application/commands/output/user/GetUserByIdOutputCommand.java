package com.erosnoxx.presenca.core.application.commands.output.user;

import com.erosnoxx.presenca.core.application.dto.entities.UserDto;
import com.erosnoxx.presenca.core.domain.entities.User;

public record GetUserByIdOutputCommand(UserDto user) {
    public static GetUserByIdOutputCommand of(User user) {
        return new GetUserByIdOutputCommand(UserDto.of(user));
    }
}
