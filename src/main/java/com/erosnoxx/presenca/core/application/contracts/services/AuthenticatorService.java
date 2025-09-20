package com.erosnoxx.presenca.core.application.contracts.services;

import com.erosnoxx.presenca.core.application.dto.auth.TokenPair;

public interface AuthenticatorService {
    TokenPair validateCredentials(String username, String password);
    TokenPair renewToken(String refreshToken);
}
