package com.erosnoxx.presenca.infrastructure.persistence.mappers;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.domain.entities.Student;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import com.erosnoxx.presenca.infrastructure.exceptions.EntityCannotBeMappedException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.AttendanceEntity;
import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.common.MapperUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentMapper implements EntityMapper<Student, StudentEntity> {
    private final ClassroomMapper classroomMapper;
    private final AttendanceMapper attendanceMapper;
    private final EntityManager em;

    public StudentMapper(
            ClassroomMapper classroomMapper,
            AttendanceMapper attendanceMapper,
            EntityManager em) {
        this.classroomMapper = classroomMapper;
        this.attendanceMapper = attendanceMapper;
        this.em = em;
    }

    @Override
    public Student toDomain(StudentEntity persistence) {
        MapperUtils.validate(persistence.getClass(), MapperUtils.MapperType.PERSISTENCE);

        var entity = new Student();

        MapperUtils.mapFromPersistence(entity, persistence);

        entity.setName(Name.of(persistence.getName()));
        entity.setRegistrationNumber(persistence.getRegistrationNumber());

        entity.setClassroom(classroomMapper.toDomain(persistence.getClassroom()));

        if (persistence.getAttendances() != null) {
            entity.setAttendances(
                    persistence.getAttendances().stream()
                            .map(attendanceMapper::toDomain)
                            .toList()
            );
        }

        return entity;
    }

    @Override
    public StudentEntity toPersistence(Student domain) {
        MapperUtils.validate(domain.getClass(), MapperUtils.MapperType.DOMAIN);

        var entity = new StudentEntity();

        MapperUtils.mapFromDomain(entity, domain);

        entity.setName(domain.getName().getValue());
        entity.setRegistrationNumber(domain.getRegistrationNumber());

        if (domain.getClassroom() != null)
            entity.setClassroom(em
                    .getReference(
                            ClassroomEntity.class,
                            domain.getClassroom().getId()
                    ));

        if (domain.getAttendances() != null) {
            var attendances = domain.getAttendances().stream()
                    .map(a -> {
                        var isUpdate = a.getId() != null;
                        return isUpdate
                                ? em.getReference(AttendanceEntity.class, a.getId())
                                : attendanceMapper.toPersistence(a);
                    })
                    .toList();
            entity.setAttendances(attendances);
        }

        return entity;
    }
}
