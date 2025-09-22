package com.erosnoxx.presenca.core.application.checkers;

import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.domain.checkers.ClassroomChecker;
import org.springframework.stereotype.Component;

@Component
public class ClassroomCheckerImpl implements ClassroomChecker {
    private final ClassroomRepository repository;

    public ClassroomCheckerImpl(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isClassNameUnique(String className) {
        return !repository.existsByClassName(className);
    }
}
