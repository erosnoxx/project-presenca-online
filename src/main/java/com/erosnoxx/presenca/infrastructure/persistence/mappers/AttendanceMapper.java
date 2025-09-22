package com.erosnoxx.presenca.infrastructure.persistence.mappers;

import com.erosnoxx.presenca.core.application.contracts.misc.EntityMapper;
import com.erosnoxx.presenca.core.domain.entities.Attendance;
import com.erosnoxx.presenca.core.domain.entities.Student;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import com.erosnoxx.presenca.infrastructure.persistence.entities.AttendanceEntity;
import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import com.erosnoxx.presenca.infrastructure.persistence.mappers.common.MapperUtils;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper implements EntityMapper<Attendance, AttendanceEntity> {
    private final EntityManager em;

    public AttendanceMapper(EntityManager em) {
        this.em = em;
    }

    @Override
    public Attendance toDomain(AttendanceEntity persistence) {
        MapperUtils.validate(persistence, MapperUtils.MapperType.PERSISTENCE);

        var entity = new Attendance();

        MapperUtils.mapFromPersistence(entity, persistence);

        entity.setDate(persistence.getDate());
        entity.setPresent(persistence.isPresent());
        entity.setReason(persistence.getReason());

        if (persistence.getStudent() != null) {
            var student = new Student(
                    Name.of(persistence.getStudent().getName()),
                    persistence.getStudent().getRegistrationNumber());

            MapperUtils.mapFromPersistence(student, persistence.getStudent());

            entity.setStudent(student);
        }

        return entity;
    }

    @Override
    public AttendanceEntity toPersistence(Attendance domain) {
        MapperUtils.validate(domain, MapperUtils.MapperType.DOMAIN);

        var entity = new AttendanceEntity();

        MapperUtils.mapFromDomain(entity, domain);

        entity.setDate(domain.getDate());
        entity.setPresent(domain.isPresent());
        entity.setReason(domain.getReason());

        entity.setStudent(em.getReference(
                StudentEntity.class,
                domain.getStudent().getId()));

        return entity;
    }
}
