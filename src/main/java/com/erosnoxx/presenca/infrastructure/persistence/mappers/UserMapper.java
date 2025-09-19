package com.erosnoxx.presenca.infrastructure.persistence.mappers;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserEntity> {
    @Override
    public User toDomain(UserEntity persistence) {
        var user = new User();
        user.setId(persistence.getId());
        user.setUsername(Username.of(persistence.getUsername()));
        user.setPassword(Password.fromEncrypted(persistence.getPassword()));
        user.setRole(persistence.getRole());
        user.setCreatedAt(persistence.getCreatedAt());
        user.setUpdatedAt(persistence.getUpdatedAt());

        return user;
    }

    @Override
    public UserEntity toPersistence(User domain) {
        var persistence = new UserEntity();
        if (domain.getId() != null) {
            persistence.setId(domain.getId());
            persistence.setCreatedAt(domain.getCreatedAt());
        }

        persistence.setUsername(domain.getUsername().getValue());
        persistence.setPassword(domain.getPassword().getValue());
        persistence.setRole(domain.getRole());

        return persistence;
    }
}
