package com.erosnoxx.presenca.core.application.contracts.repositories;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Repository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.ClassroomCriteria;
import com.erosnoxx.presenca.core.domain.entities.Classroom;

import java.util.UUID;

public interface ClassroomRepository extends Repository<Classroom, UUID, ClassroomCriteria> {
}
