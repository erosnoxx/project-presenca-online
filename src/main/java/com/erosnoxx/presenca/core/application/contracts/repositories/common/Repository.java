package com.erosnoxx.presenca.core.application.contracts.repositories.common;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface Repository<D extends DomainEntity<I>, I, C extends Criteria> {
    D save(D entity);
    List<D> saveBatch(Iterable<D> entities);

    void delete(D entity);
    void deleteAll(Iterable<D> entities);
    void deleteAll();

    Optional<D> findById(I id);
    boolean existsById(I id);

    long count();
    long count(C criteria);

    Page<D> findAll(Pageable pageable);
    List<D> findAll();
    Page<D> findAll(C criteria, Pageable pageable);
}
