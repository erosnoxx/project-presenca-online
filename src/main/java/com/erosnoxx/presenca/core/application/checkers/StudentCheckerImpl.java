package com.erosnoxx.presenca.core.application.checkers;

import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.domain.checkers.StudentChecker;
import org.springframework.stereotype.Component;

@Component
public class StudentCheckerImpl implements StudentChecker {
    private final StudentRepository repository;

    public StudentCheckerImpl(StudentRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean isRegistrationNumberUnique(String registrationNumber) {
        return !repository.existsByRegistrationNumber(registrationNumber);
    }
}
