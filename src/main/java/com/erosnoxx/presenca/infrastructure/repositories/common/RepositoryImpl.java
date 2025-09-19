package com.erosnoxx.presenca.infrastructure.repositories.common;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.application.contracts.repositories.common.Criteria;
import com.erosnoxx.presenca.core.application.contracts.repositories.common.Persistence;
import com.erosnoxx.presenca.core.application.contracts.repositories.common.Repository;
import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl<D extends DomainEntity<I>, I,
        C extends Criteria, P extends Persistence<I>,
        R extends JpaRepository<P, I>>
        implements Repository<D, I, C> {
    protected final R repository;
    protected final EntityManager em;
    protected final EntityMapper<D, P> mapper;
    protected final Class<P> entityClass;

    public RepositoryImpl(
            R repository,
            EntityManager em,
            EntityMapper<D, P> mapper,
            Class<P> entityClass) {
        this.repository = repository;
        this.em = em;
        this.mapper = mapper;
        this.entityClass = entityClass;
    }

    @Override
    public D save(D entity) {
        var jpaEntity = mapper.toPersistence(entity);
        var saved = repository.save(jpaEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<D> saveBatch(Iterable<D> entities) {
        List<P> toSave =
                ((List<D>) entities).stream()
                        .map(mapper::toPersistence)
                        .toList();

        List<P> saved = repository.saveAll(toSave);
        return saved.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(D entity) {
        repository.delete(mapper.toPersistence(entity));
    }

    @Override
    public void deleteAll(Iterable<D> entities) {
        List<P> toDelete =
                ((List<D>) entities).stream()
                        .map(mapper::toPersistence)
                        .toList();
        repository.deleteAll(toDelete);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<D> findById(I id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(I id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long count(C criteria) {
        var cb = em.getCriteriaBuilder();
        var countQuery = cb.createQuery(Long.class);
        var root = countQuery.from(entityClass);

        countQuery.select(cb.count(root))
                .where(CriteriaUtils.buildPredicates(criteria, root, cb));

        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Page<D> findAll(C criteria, Pageable pageable) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(entityClass);
        var root = query.from(entityClass);

        query.where(CriteriaUtils.buildPredicates(criteria, root, cb));

        var typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<D> content = typedQuery.getResultList().stream()
                .map(mapper::toDomain)
                .toList();

        long total = count(criteria);

        return new PageImpl<>(content, pageable, total);
    }
}
