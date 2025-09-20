package com.erosnoxx.presenca.unit.application.usecases;

import com.erosnoxx.presenca.core.application.commands.input.auth.LoginInputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.UserRepository;
import com.erosnoxx.presenca.core.application.contracts.services.AuthenticatorService;
import com.erosnoxx.presenca.core.application.dto.auth.TokenPair;
import com.erosnoxx.presenca.core.application.usecases.auth.LoginUseCaseImpl;
import com.erosnoxx.presenca.core.domain.entities.User;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserNotAuthenticatedException;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUseCaseImplTest {

    private UserRepository repository;
    private AuthenticatorService authenticatorService;
    private LoginUseCaseImpl loginUseCase;
    private static final String VALID_PWD = "N1c0t1n@";
    private static final Password ENCRYPTED_VALID_PWD = Password
            .fromEncrypted(Password.fromPlainText(VALID_PWD).getValue());

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        authenticatorService = mock(AuthenticatorService.class);
        loginUseCase = new LoginUseCaseImpl(authenticatorService, repository);
    }

    @Test
    void shouldLoginSuccessfully() {
        var input = new LoginInputCommand("john", VALID_PWD);
        var user = mock(User.class);

        when(repository.findByUsername("john")).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn(ENCRYPTED_VALID_PWD);

        var pair = new TokenPair(
                "access",
                "refresh",
                ZonedDateTime.now().plusHours(1));

        when(authenticatorService.validateCredentials("john", VALID_PWD))
                .thenReturn(pair);

        var result = loginUseCase.execute(input);

        assertNotNull(result);
        assertEquals("access", result.accessToken());
        assertEquals("refresh", result.refreshToken());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        var input = new LoginInputCommand("ghost", "xxx");

        when(repository.findByUsername("ghost")).thenReturn(Optional.empty());

        assertThrows(UserNotAuthenticatedException.class, () -> loginUseCase.execute(input));
    }

    @Test
    void shouldThrowWhenPasswordDoesNotMatch() {
        var input = new LoginInputCommand("john", "Wr0ngP@ss");
        var user = mock(User.class);

        when(repository.findByUsername("john")).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn(ENCRYPTED_VALID_PWD);

        assertThrows(UserNotAuthenticatedException.class,
                () -> loginUseCase.execute(input));
    }
}
