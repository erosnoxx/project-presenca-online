package com.erosnoxx.presenca.core.application.dto.entities;

import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static UserDto of(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername().getValue(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
