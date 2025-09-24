package com.erosnoxx.presenca.infrastructure.repositories.common;

import com.erosnoxx.presenca.core.application.annotations.CriteriaField;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.List;

public class CriteriaUtils {

    public static <E, C> Predicate[] buildPredicates(
            C criteria,
            Root<E> root,
            CriteriaBuilder cb
    ) {
        List<Predicate> predicates = new ArrayList<>();

        for (RecordComponent component : criteria.getClass().getRecordComponents()) {
            CriteriaField annotation = component.getAnnotation(CriteriaField.class);
            if (annotation == null) continue; // pega só os anotados

            try {
                Object value = component.getAccessor().invoke(criteria);
                if (value == null) continue;

                String[] pathParts = annotation.value().split("\\.");
                Path<Object> path = root.get(pathParts[0]);
                for (int i = 1; i < pathParts.length; i++) {
                    path = path.get(pathParts[i]);
                }

                predicates.add(cb.equal(path, value));

            } catch (Exception e) {
                throw new RuntimeException("error building predicates for " + component.getName(), e);
            }
        }

        return predicates.toArray(new Predicate[0]);
    }
}
