package com.erosnoxx.presenca.infrastructure.persistence.entities;

import com.erosnoxx.presenca.core.domain.enums.AbsenceReason;
import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceEntity extends JpaEntity<UUID> {
    private LocalDate date;
    private boolean present;
    private AbsenceReason reason;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;
}
