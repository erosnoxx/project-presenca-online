package com.erosnoxx.presenca.core.application.contracts.repositories.criteria;

import com.erosnoxx.presenca.core.application.annotations.CriteriaField;
import com.erosnoxx.presenca.core.application.contracts.repositories.common.Criteria;

public record ClassroomCriteria(
        @CriteriaField(value = "name")
        String name) implements Criteria {
}
