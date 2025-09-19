package com.erosnoxx.presenca.unit.domain.factories;

import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UserFactory {

    private static final AtomicInteger counter = new AtomicInteger(1);

    public static User createValidUser(UserChecker checker) {
        String username = "user.test";
        String password = "Passw0rd!";
        return User.create(username, password, Role.USER, checker);
    }

    public static User createAdmin(UserChecker checker) {
        String username = "admin.user";
        String password = "Adm1nP@ss!";
        return User.create(username, password, Role.ADMIN, checker);
    }

    public static User withId(UUID id, UserChecker checker) {
        User user = createValidUser(checker);
        user.setId(id);
        return user;
    }
}
