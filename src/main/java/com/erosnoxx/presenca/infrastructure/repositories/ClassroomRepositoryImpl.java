package com.erosnoxx.presenca.infrastructure.repositories;

import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.ClassroomCriteria;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.ClassroomMapper;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.ClassroomJpaRepository;
import com.erosnoxx.presenca.infrastructure.repositories.common.RepositoryImpl;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class ClassroomRepositoryImpl
        extends RepositoryImpl<Classroom, UUID, ClassroomCriteria, ClassroomEntity, ClassroomJpaRepository>
        implements ClassroomRepository {
    public ClassroomRepositoryImpl(
            ClassroomJpaRepository repository,
            EntityManager em,
            ClassroomMapper mapper) {
        super(repository, em, mapper, ClassroomEntity.class);
    }
}
