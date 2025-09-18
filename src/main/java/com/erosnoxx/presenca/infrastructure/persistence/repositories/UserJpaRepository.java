package com.erosnoxx.presenca.infrastructure.persistence.repositories;

import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findUserByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
