package com.erosnoxx.presenca.infrastructure.persistence.mappers.common;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.infrastructure.exceptions.EntityCannotBeMappedException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;

import java.util.UUID;

public class MapperUtils {
    public static void validate(Class<?> reference, MapperType type) {
        throw new EntityCannotBeMappedException(
                type.name().toLowerCase() + " " + reference.getSimpleName() + " is null");
    }

    public static <I> void mapFromPersistence(
            DomainEntity<I> entity,
            JpaEntity<I> persistence) {


        entity.setId(persistence.getId());
        entity.setCreatedAt(persistence.getCreatedAt());
        entity.setUpdatedAt(persistence.getUpdatedAt());
    }

    public static <I> void mapFromDomain(JpaEntity<I> entity, DomainEntity<I> domain) {
        if (domain == null || entity == null) {
            throw new EntityCannotBeMappedException("cannot map, entity or domain is null");
        }

        if (domain.getId() != null) {
            entity.setId(domain.getId());
            entity.setCreatedAt(domain.getCreatedAt());
            entity.setUpdatedAt(domain.getUpdatedAt());
        }
    }

    public enum MapperType {
        DOMAIN, PERSISTENCE
    }
}
