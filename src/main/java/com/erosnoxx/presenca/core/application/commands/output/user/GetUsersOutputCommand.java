package com.erosnoxx.presenca.core.application.commands.output.user;

import com.erosnoxx.presenca.core.application.dto.user.UserDto;
import com.erosnoxx.presenca.core.domain.entities.User;
import org.springframework.data.domain.Page;


public record GetUsersOutputCommand(Page<UserDto> users) {
    public static GetUsersOutputCommand of(Page<User> users) {
        return new GetUsersOutputCommand(users.map(UserDto::of));
    }
}
