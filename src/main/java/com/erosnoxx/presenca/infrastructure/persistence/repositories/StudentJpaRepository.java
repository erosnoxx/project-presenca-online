package com.erosnoxx.presenca.infrastructure.persistence.repositories;

import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, UUID> {
}
