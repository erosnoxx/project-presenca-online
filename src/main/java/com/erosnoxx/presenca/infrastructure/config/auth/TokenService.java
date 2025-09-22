package com.erosnoxx.presenca.infrastructure.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.erosnoxx.presenca.core.application.dto.auth.TokenPair;
import com.erosnoxx.presenca.infrastructure.exceptions.InternalServerErrorException;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.*;

@Service @Slf4j
public class TokenService {
    @Value("${api.security.token.secret}")
    private String SECRET;
    @Value("${api.security.token.expirationTimeInHours}")
    private int ACCESS_TOKEN_HOURS;
    @Value("${api.security.token.refreshExpirationTimeInHours}")
    private int REFRESH_TOKEN_HOURS;
    private static final String ISSUER = "auth-api";

    public TokenPair generateToken(String username) {
        try {
            var algorithm = Algorithm.HMAC256(SECRET);

            var accessExpiresAt = genExpirationDate(ACCESS_TOKEN_HOURS);
            var refreshExpiresAt = genExpirationDate(REFRESH_TOKEN_HOURS);

            var accessToken = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(username)
                    .withExpiresAt(accessExpiresAt)
                    .sign(algorithm);

            var refreshToken = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(username)
                    .withExpiresAt(refreshExpiresAt)
                    .sign(algorithm);

            return new TokenPair(accessToken, refreshToken, getExpiresAtLocal(accessExpiresAt));
        } catch(JWTCreationException exception) {
            throw new InternalServerErrorException("Error while generating token: " + exception);
        }
    }

    public TokenPair refreshAccessToken(String refreshToken) {
        var username = validateToken(refreshToken);
        if (username.isEmpty()) return null;

        var pair = generateToken(username);

        return new TokenPair(pair.accessToken(), refreshToken, pair.expiresAt());
    }

    public String validateToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            log.error(ex.getMessage());
            return "";
        }
    }

    private Instant genExpirationDate(long hours){
        return Instant.now().plusSeconds(hours * 3600);
    }

    public ZonedDateTime getExpiresAtLocal(Instant expiresAt) {
        return expiresAt.atZone(ZoneId.of("America/Sao_Paulo"));
    }
}
