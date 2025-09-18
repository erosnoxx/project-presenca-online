package com.erosnoxx.presenca.infrastructure.persistence.entities.common;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class JpaEntity<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

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
