package com.erosnoxx.presenca.infrastructure.config.security;

public class SecurityMatcher {
    public static String[] getPublicEndpoints() {
        return new String[] {
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/auth/login",
        };
    }
}
