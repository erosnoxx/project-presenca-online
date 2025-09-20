package com.erosnoxx.presenca.infrastructure.services;

import com.erosnoxx.presenca.core.application.contracts.services.AuthenticatorService;
import com.erosnoxx.presenca.core.application.dto.auth.TokenPair;
import com.erosnoxx.presenca.infrastructure.config.auth.TokenService;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticatorServiceImpl(
            TokenService tokenService,
            AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenPair validateCredentials(String username, String password) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                username, password);

        var auth = (UserEntity) authenticationManager
                .authenticate(usernamePassword).getPrincipal();

        return tokenService.generateToken(auth.getUsername());
    }

    @Override
    public TokenPair renewToken(String refreshToken) {
        return tokenService.refreshAccessToken(refreshToken);
    }
}
