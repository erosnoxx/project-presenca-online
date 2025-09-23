package com.erosnoxx.presenca.core.application.contracts.repositories;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Repository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.StudentCriteria;
import com.erosnoxx.presenca.core.domain.entities.Student;

import java.util.UUID;

public interface StudentRepository extends Repository<Student, UUID, StudentCriteria> {
    boolean existsByRegistrationNumber(String registrationNumber);
}
