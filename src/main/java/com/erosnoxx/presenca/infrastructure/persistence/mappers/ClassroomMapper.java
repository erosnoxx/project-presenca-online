package com.erosnoxx.presenca.infrastructure.persistence.mappers;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.common.MapperUtils;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper implements EntityMapper<Classroom, ClassroomEntity> {
    private final StudentMapper studentMapper;
    private final EntityManager em;

    public ClassroomMapper(StudentMapper studentMapper, EntityManager em) {
        this.studentMapper = studentMapper;
        this.em = em;
    }

    @Override
    public Classroom toDomain(ClassroomEntity persistence) {
        MapperUtils.validate(persistence.getClass(), MapperUtils.MapperType.PERSISTENCE);

        var entity = new Classroom();

        MapperUtils.mapFromPersistence(entity, persistence);

        entity.setClassName(Name.of(persistence.getClassName()));
        if (entity.getStudents() != null)
            entity.setStudents(persistence.getStudents()
                    .stream().map(studentMapper::toDomain)
                    .toList());

        return entity;
    }

    @Override
    public ClassroomEntity toPersistence(Classroom domain) {
        MapperUtils.validate(domain.getClass(), MapperUtils.MapperType.DOMAIN);

        var entity = new ClassroomEntity();

        MapperUtils.mapFromDomain(entity, domain);

        entity.setClassName(domain.getClassName().getValue());

        if (domain.getStudents() != null) {
            var students = domain.getStudents().stream()
                    .map(s -> {
                        var isUpdate = s.getId() != null;
                        return isUpdate
                                ? em.getReference(StudentEntity.class, s.getId())
                                : studentMapper.toPersistence(s);
                    })
                    .toList();
            entity.setStudents(students);
        }

        return entity;
    }
}
