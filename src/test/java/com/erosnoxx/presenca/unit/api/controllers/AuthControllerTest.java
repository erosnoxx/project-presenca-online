package com.erosnoxx.presenca.unit.api.controllers;

import com.erosnoxx.presenca.api.controllers.AuthController;
import com.erosnoxx.presenca.api.schemas.request.auth.LoginRequest;
import com.erosnoxx.presenca.api.schemas.response.auth.LoginResponse;
import com.erosnoxx.presenca.core.application.commands.input.auth.LoginInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.auth.LoginOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.usecases.auth.LoginUseCase;
import com.erosnoxx.presenca.core.domain.exceptions.entities.UserNotAuthenticatedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AuthControllerTest {

    private final LoginUseCase loginUseCase = Mockito.mock(LoginUseCase.class);
    private final AuthController controller = new AuthController(loginUseCase);

    @Test
    void shouldReturnResponseEntityWithLoginResponse() {
        var output = new LoginOutputCommand(
                "access-token-123",
                "refresh-token-456",
                ZonedDateTime.now().plusHours(1)
        );

        Mockito.when(loginUseCase.execute(any(LoginInputCommand.class)))
                .thenReturn(output);

        var request = new LoginRequest("john", "Valid123!");

        ResponseEntity<LoginResponse> response = controller.login(request);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("access-token-123", response.getBody().accessToken());
        assertEquals("refresh-token-456", response.getBody().refreshToken());
    }

    @Test
    void shouldPropagateWhenLoginFails() {
        Mockito.when(loginUseCase.execute(any(LoginInputCommand.class)))
                .thenThrow(new UserNotAuthenticatedException("invalid credentials"));

        var request = new LoginRequest("john", "wrongpass");

        assertThrows(
                UserNotAuthenticatedException.class,
                () -> controller.login(request)
        );
    }
}
