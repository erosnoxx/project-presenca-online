package com.erosnoxx.presenca.core.application.checkers;

import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserCheckerImpl implements UserChecker {
    private final UserRepository repository;

    public UserCheckerImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !repository.existsByUsername(username);
    }

    @Override
    public boolean adminExists() {
        return repository.existsByRole(Role.ADMIN);
    }
}
