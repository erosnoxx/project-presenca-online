package com.erosnoxx.presenca.core.application.contracts.repositories;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Repository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.UserCriteria;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID, UserCriteria> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByRole(Role role);
}
