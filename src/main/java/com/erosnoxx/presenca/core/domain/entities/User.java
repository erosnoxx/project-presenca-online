package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserAlreadyExistsException;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User extends DomainEntity<UUID> {
    private Username username;
    private Password password;
    private Role role;

    public static User create(
            String username,
            String password,
            Role role,
            UserChecker checker) {
        if (!checker.isUsernameUnique(username))
            throw new UserAlreadyExistsException("username already registered");

        if (role == Role.ADMIN && checker.adminExists())
            throw new UserAlreadyExistsException("admin user already registered");

        return new User(
                Username.of(username),
                Password.fromPlainText(password),
                role);
    }

    public User(UUID id) {
        setId(id);
    }
}
