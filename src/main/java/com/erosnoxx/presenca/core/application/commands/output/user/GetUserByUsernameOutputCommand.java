package com.erosnoxx.presenca.core.application.commands.output.user;

import com.erosnoxx.presenca.core.application.dto.entities.UserDto;
import com.erosnoxx.presenca.core.domain.entities.User;

public record GetUserByUsernameOutputCommand(UserDto user) {
    public static GetUserByUsernameOutputCommand of(User user) {
        return new GetUserByUsernameOutputCommand(UserDto.of(user));
    }
}
