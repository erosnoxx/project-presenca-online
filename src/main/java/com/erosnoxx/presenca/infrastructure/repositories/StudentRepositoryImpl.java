package com.erosnoxx.presenca.infrastructure.repositories;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.StudentCriteria;
import com.erosnoxx.presenca.core.domain.entities.Student;
import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.StudentMapper;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.StudentJpaRepository;
import com.erosnoxx.presenca.infrastructure.repositories.common.RepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class StudentRepositoryImpl
        extends RepositoryImpl<Student, UUID, StudentCriteria, StudentEntity, StudentJpaRepository>
        implements StudentRepository {
    public StudentRepositoryImpl(
            StudentJpaRepository repository,
            EntityManager em,
            StudentMapper mapper) {
        super(repository, em, mapper, StudentEntity.class);
    }
}
