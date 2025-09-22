package com.erosnoxx.presenca.core.application.contracts.repositories.criteria;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Criteria;

public record StudentCriteria(String name, String registrationNumber)
    implements Criteria {
}
