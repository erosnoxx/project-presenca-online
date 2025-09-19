package com.erosnoxx.presenca.core.application.contracts.misc;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Persistence;
import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;

public interface EntityMapper<D extends DomainEntity<?>, P extends Persistence<?>> {
    D toDomain(P persistence);
    P toPersistence(D domain);
}
