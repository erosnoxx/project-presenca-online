package com.erosnoxx.presenca.api.schemas.response.common;

import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;

import java.util.UUID;

public record UUIDResponse(UUID id) {
    public static UUIDResponse fromOutput(UUIDOutputCommand output) {
        return new UUIDResponse(output.id());
    }
}
