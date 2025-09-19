package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.auth.LoginRequest;
import com.erosnoxx.presenca.api.schemas.response.auth.LoginResponse;
import com.erosnoxx.presenca.core.application.contracts.usecases.auth.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("auth")
public class AuthController {
    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(LoginResponse
                        .fromOutput(loginUseCase.execute(request.toInput())));
    }
}
