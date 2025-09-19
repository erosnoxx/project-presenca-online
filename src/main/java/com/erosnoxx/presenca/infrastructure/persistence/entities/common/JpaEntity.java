package com.erosnoxx.presenca.infrastructure.persistence.entities.common;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Persistence;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass @Getter @Setter
public abstract class JpaEntity<I> implements Persistence<I> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private I id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
