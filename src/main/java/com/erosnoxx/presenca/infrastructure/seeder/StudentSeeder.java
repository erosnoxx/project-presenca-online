package com.erosnoxx.presenca.infrastructure.seeder;

import com.erosnoxx.presenca.infrastructure.exceptions.InternalServerErrorException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.StudentEntity;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.ClassroomJpaRepository;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.StudentJpaRepository;
import com.erosnoxx.presenca.infrastructure.seeder.common.Seeder;
import com.erosnoxx.presenca.infrastructure.seeder.dto.StudentProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component @Slf4j
public class StudentSeeder extends Seeder<StudentEntity, UUID, StudentJpaRepository> {
    @Value("${app.seeder.file-path}")
    private String seederFile;
    private final ClassroomJpaRepository classroomRepository;

    public StudentSeeder(
            StudentJpaRepository repository,
            ResourceLoader resourceLoader,
            ClassroomJpaRepository classroomRepository) {
        super(repository, resourceLoader);
        this.classroomRepository = classroomRepository;
    }

    @Override
    protected void seed() {
        loadSeederFile(seederFile);
        createStudents(getAsList("students", StudentProps.class));
    }

    private void createStudents(List<StudentProps> students) {
        var entities = students.stream()
                        .map(props -> {
                            var classroom = classroomRepository
                                    .findByClassName(props.className())
                                    .orElseThrow(() -> new InternalServerErrorException(
                                            "invalid className in students seeder: " + props.className()));

                            var entity = new StudentEntity();
                            entity.setName(props.name());
                            entity.setRegistrationNumber(props.registrationNumber());
                            entity.setClassroom(classroom);
                            return entity;
                        })
                        .toList();

        repository.saveAll(entities);
        log.warn("⚡ {} Students created", (long) entities.size());
    }
}
