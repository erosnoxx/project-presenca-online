package com.erosnoxx.presenca.core.application.contracts.repositories.criteria;

import com.erosnoxx.presenca.core.application.contracts.repositories.common.Criteria;
import com.erosnoxx.presenca.core.domain.enums.Role;

public record UserCriteria(Role role) implements Criteria {
}
