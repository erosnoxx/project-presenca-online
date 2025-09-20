package com.erosnoxx.presenca.api.schemas.request.user;

import jakarta.annotation.Nullable;

public record UpdateUserRequest(
        @Nullable String username,
        @Nullable String password) {
}
