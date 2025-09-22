package com.erosnoxx.presenca.core.application.commands.output.common;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;

import java.util.UUID;

public record UUIDOutputCommand(UUID id) {
    public static UUIDOutputCommand of(DomainEntity<UUID> domain) {
        return new UUIDOutputCommand(domain.getId());
    }
}
