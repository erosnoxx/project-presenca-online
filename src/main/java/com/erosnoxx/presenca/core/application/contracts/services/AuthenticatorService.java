package com.erosnoxx.presenca.core.application.contracts.services;

import com.erosnoxx.presenca.core.application.dto.TokenPair;

public interface AuthenticatorService {
    TokenPair validateCredentials(String username, String password);
}
