package com.erosnoxx.presenca.api.schemas.request.user;

import java.util.Optional;

public record UpdateUserRequest(
        Optional<String> username,
        Optional<String> password) {
}
