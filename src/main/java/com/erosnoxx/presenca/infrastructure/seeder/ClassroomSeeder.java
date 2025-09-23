package com.erosnoxx.presenca.infrastructure.seeder;

import com.erosnoxx.presenca.infrastructure.persistence.entities.ClassroomEntity;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.ClassroomJpaRepository;
import com.erosnoxx.presenca.infrastructure.seeder.common.Seeder;
import com.erosnoxx.presenca.infrastructure.seeder.dto.ClassroomProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component @Slf4j
public class ClassroomSeeder extends Seeder<ClassroomEntity, UUID, ClassroomJpaRepository> {
    @Value("${app.seeder.file-path}")
    private String seederFile;


    public ClassroomSeeder(
            ClassroomJpaRepository repository,
            ResourceLoader resourceLoader) {
        super(repository, resourceLoader);
    }

    @Override
    protected void seed() {
        loadSeederFile(seederFile);
        createClassrooms(
                getAsList("classrooms", ClassroomProps.class));
    }

    private void createClassrooms(List<ClassroomProps> classrooms) {
        var entities = classrooms.stream()
                .map(props -> {
                    var entity = new ClassroomEntity();
                    entity.setClassName(props.className());
                    return entity;
                })
                .toList();

        repository.saveAll(entities);
        log.warn("⚡ {} Classrooms created", (long) entities.size());
    }
}
