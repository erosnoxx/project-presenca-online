package com.erosnoxx.presenca.infrastructure.persistence.repositories;

import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassroomJpaRepository extends JpaRepository<ClassroomEntity, UUID> {
}
