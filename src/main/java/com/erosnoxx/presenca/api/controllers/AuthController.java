package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.auth.LoginRequest;
import com.erosnoxx.presenca.api.schemas.request.auth.RefreshTokenRequest;
import com.erosnoxx.presenca.api.schemas.response.auth.LoginResponse;
import com.erosnoxx.presenca.api.schemas.response.auth.RefreshTokenResponse;
import com.erosnoxx.presenca.core.application.contracts.usecases.auth.LoginUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.auth.RefreshTokenUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AuthController(
            LoginUseCase loginUseCase,
            RefreshTokenUseCase refreshTokenUseCase) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(LoginResponse
                        .fromOutput(loginUseCase.execute(request.toInput())));
    }

    @PostMapping("refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(RefreshTokenResponse.of(
                refreshTokenUseCase.execute(request.toInput())
        ));
    }
}
