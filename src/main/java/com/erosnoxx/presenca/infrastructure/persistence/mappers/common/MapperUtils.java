package com.erosnoxx.presenca.infrastructure.persistence.mappers.common;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.infrastructure.exceptions.EntityCannotBeMappedException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;


public class MapperUtils {
    public static void validate(Object entity, MapperType type) {
        if (entity == null)
            throw new EntityCannotBeMappedException(
                    type.name().toLowerCase() + " entity is null");
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
