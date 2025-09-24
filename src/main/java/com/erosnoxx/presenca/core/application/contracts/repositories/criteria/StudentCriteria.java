package com.erosnoxx.presenca.core.application.contracts.repositories.criteria;

import com.erosnoxx.presenca.core.application.annotations.CriteriaField;
import com.erosnoxx.presenca.core.application.contracts.repositories.common.Criteria;

import java.util.UUID;

public record StudentCriteria(
        @CriteriaField(value = "classroom.id")
        UUID classroomId
)
    implements Criteria {
}
