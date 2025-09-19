package com.erosnoxx.presenca.infrastructure.repositories;

import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.repositories.criteria.UserCriteria;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.UserMapper;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.UserJpaRepository;
import com.erosnoxx.presenca.infrastructure.repositories.common.RepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl
        extends RepositoryImpl<User, UUID, UserCriteria, UserEntity, UserJpaRepository>
        implements UserRepository {

    public UserRepositoryImpl(
            UserJpaRepository repository,
            EntityManager em,
            UserMapper mapper) {
        super(repository, em, mapper, UserEntity.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByRole(Role role) {
        return repository.existsByRole(role);
    }
}
