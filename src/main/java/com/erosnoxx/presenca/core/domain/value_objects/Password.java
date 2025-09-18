package com.erosnoxx.presenca.core.domain.value_objects;


import com.erosnoxx.presenca.core.domain.exceptions.value_objects.InvalidPasswordException;
import com.erosnoxx.presenca.core.domain.value_objects.common.ValueObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class Password extends ValueObject<String> {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z\\d])(?=.*\\W).{8,}$");

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 100;

    private static final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    private final boolean isEncrypted;

    private Password(String value, boolean isEncrypted) {
        super(value);
        this.isEncrypted = isEncrypted;
    }

    public static Password fromPlainText(String plainPassword) {
        validatePlainPassword(plainPassword);

        String encrypted = bCrypt.encode(plainPassword.trim());
        return new Password(encrypted, true);
    }

    public static Password fromEncrypted(String encryptedPassword) {
        if (encryptedPassword == null || encryptedPassword.trim().isEmpty()) {
            throw new InvalidPasswordException("Encrypted password cannot be null or empty");
        }

        return new Password(encryptedPassword.trim(), true);
    }

    private static void validatePlainPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new InvalidPasswordException("Password cannot be null or empty");
        }

        String trimmed = plainPassword.trim();

        if (trimmed.length() < MIN_LENGTH) {
            throw new InvalidPasswordException("Password must be at least " + MIN_LENGTH + " characters long");
        }

        if (trimmed.length() > MAX_LENGTH) {
            throw new InvalidPasswordException("Password cannot be longer than " + MAX_LENGTH + " characters");
        }

        if (!PASSWORD_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidPasswordException(
                    "Password must contain at least one letter, one number and one special character");
        }
    }

    public boolean matches(String plainPassword) {
        if (!isEncrypted) {
            throw new IllegalStateException("Cannot match against non-encrypted password");
        }
        return bCrypt.matches(plainPassword, this.value);
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    @Override
    public String toString() {
        return isEncrypted ? "[ENCRYPTED]" : "[PLAIN_TEXT]";
    }

    @Override
    protected String validate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidPasswordException("Password cannot be null or empty");
        }
        return value.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Password password = (Password) obj;
        return isEncrypted && password.isEncrypted && Objects.equals(value, password.value);
    }

    public static Password generateRandomPassword() {
        var raw = UUID.randomUUID().toString().substring(0, 12);
        return Password.fromPlainText(raw);
    }
}
