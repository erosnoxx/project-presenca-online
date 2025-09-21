package com.erosnoxx.presenca.infrastructure.persistence.entities;

import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "classrooms")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClassroomEntity extends JpaEntity<UUID> {
    @Column(name = "class_name")
    private String className;

    @OneToMany(mappedBy = "classroom", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<StudentEntity> students;
}
