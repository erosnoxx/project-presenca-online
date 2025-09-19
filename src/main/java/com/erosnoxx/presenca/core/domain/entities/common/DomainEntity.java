package com.erosnoxx.presenca.core.domain.entities.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public abstract class DomainEntity<I> {
    private I id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
