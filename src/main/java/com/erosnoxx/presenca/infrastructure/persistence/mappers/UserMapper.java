package com.erosnoxx.presenca.infrastructure.persistence.mappers;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import com.erosnoxx.presenca.infrastructure.exceptions.EntityCannotBeMappedException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.common.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserEntity> {
    @Override
    public User toDomain(UserEntity persistence) {
        MapperUtils.validate(persistence.getClass(), MapperUtils.MapperType.PERSISTENCE);

        var entity = new User();

        MapperUtils.mapFromPersistence(entity, persistence);

        entity.setUsername(Username.of(persistence.getUsername()));
        entity.setPassword(Password.fromEncrypted(persistence.getPassword()));
        entity.setRole(persistence.getRole());

        return entity;
    }

    @Override
    public UserEntity toPersistence(User domain) {
        MapperUtils.validate(domain.getClass(), MapperUtils.MapperType.DOMAIN);

        var entity = new UserEntity();

        MapperUtils.mapFromDomain(entity, domain);

        entity.setUsername(domain.getUsername().getValue());
        entity.setPassword(domain.getPassword().getValue());
        entity.setRole(domain.getRole());

        return entity;
    }
}
