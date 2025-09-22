package com.erosnoxx.presenca.infrastructure.persistence.entities;

import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity @Table(name = "students")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StudentEntity extends JpaEntity<UUID> {
    private String name;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassroomEntity classroom;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade =
            {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AttendanceEntity> attendances;
}
