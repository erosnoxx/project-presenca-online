package com.erosnoxx.presenca.unit.domain.entities;

import com.erosnoxx.presenca.core.domain.checkers.UserChecker;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserAlreadyExistsException;
import com.erosnoxx.presenca.unit.domain.factories.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UserChecker checker;

    @BeforeEach
    void setup() {
        checker = Mockito.mock(UserChecker.class);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        Mockito.when(checker.isUsernameUnique(Mockito.anyString())).thenReturn(true);
        Mockito.when(checker.adminExists()).thenReturn(false);

        User user = UserFactory.createValidUser(checker);

        assertNotNull(user);
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void shouldFailWhenUsernameAlreadyExists() {
        Mockito.when(checker.isUsernameUnique(Mockito.anyString())).thenReturn(false);

        UserAlreadyExistsException ex = assertThrows(
                UserAlreadyExistsException.class,
                () -> UserFactory.createValidUser(checker)
        );

        assertEquals("username already registered", ex.getMessage());
    }

    @Test
    void shouldFailWhenAdminAlreadyExists() {
        Mockito.when(checker.isUsernameUnique(Mockito.anyString())).thenReturn(true);
        Mockito.when(checker.adminExists()).thenReturn(true);

        UserAlreadyExistsException ex = assertThrows(
                UserAlreadyExistsException.class,
                () -> UserFactory.createAdmin(checker)
        );

        assertEquals("admin user already registered", ex.getMessage());
    }
}
