package com.erosnoxx.presenca.core.application.dto.user;

import com.erosnoxx.presenca.core.domain.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static UserDto of(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername().getValue(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
