package com.erosnoxx.presenca.infrastructure.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.erosnoxx.presenca.core.application.dto.TokenPair;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.*;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private static final long ACCESS_TOKEN_HOURS = 3;
    private static final long REFRESH_TOKEN_DAYS = 1;

    public TokenPair generateToken(UserEntity user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            var accessExpiresAt = genExpirationDate(ACCESS_TOKEN_HOURS);
            var refreshExpiresAt = genExpirationDateDays(REFRESH_TOKEN_DAYS);

            var accessToken = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(accessExpiresAt)
                    .sign(algorithm);

            var refreshToken = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(refreshExpiresAt)
                    .sign(algorithm);

            return new TokenPair(accessToken, refreshToken, getExpiresAtLocal(accessExpiresAt));
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate(long hours){
        return Instant.now().plusSeconds(hours * 3600);
    }

    private Instant genExpirationDateDays(long days){
        return Instant.now().plusSeconds(days * 24 * 3600);
    }

    public ZonedDateTime getExpiresAtLocal(Instant expiresAt) {
        return expiresAt.atZone(ZoneId.of("America/Sao_Paulo"));
    }
}
