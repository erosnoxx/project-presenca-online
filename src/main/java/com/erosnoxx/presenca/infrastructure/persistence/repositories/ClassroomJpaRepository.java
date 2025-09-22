package com.erosnoxx.presenca.infrastructure.persistence.repositories;

import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassroomJpaRepository extends JpaRepository<ClassroomEntity, UUID> {
    Optional<ClassroomEntity> findByClassName(String className);
    boolean existsByClassName(String className);
}
