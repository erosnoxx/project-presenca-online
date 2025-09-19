package com.erosnoxx.presenca.unit.domain.value_objects;

import com.erosnoxx.presenca.core.domain.exceptions.value_objects.InvalidPasswordException;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


class PasswordTest {

    @Test
    void shouldCreateEncryptedPasswordFromPlainText() {
        Password password = Password.fromPlainText("Abcdef1!");
        assertTrue(password.isEncrypted());
        assertNotEquals("Abcdef1!", password.toString());
        assertTrue(password.matches("Abcdef1!"));
    }

    @Test
    void shouldThrowOnNullOrEmptyPlainPassword() {
        InvalidPasswordException ex1 = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromPlainText(null)
        );
        assertTrue(Objects.requireNonNull(ex1.toProblemDetail().getDetail())
                .contains("cannot be null or empty"));

        InvalidPasswordException ex2 = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromPlainText("   ")
        );
        assertTrue(Objects.requireNonNull(ex2.toProblemDetail().getDetail())
                .contains("cannot be null or empty"));
    }

    @Test
    void shouldThrowOnTooShortOrTooLongPassword() {
        InvalidPasswordException exShort = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromPlainText("A1!")
        );
        assertTrue(Objects.requireNonNull(exShort.toProblemDetail().getDetail())
                .contains("at least 8 characters"));

        String longPassword = "A1!" + "a".repeat(200);
        InvalidPasswordException exLong = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromPlainText(longPassword)
        );
        assertTrue(Objects.requireNonNull(exLong.toProblemDetail().getDetail())
                .contains("cannot be longer than 100 characters"));
    }

    @Test
    void shouldThrowOnInvalidPattern() {
        InvalidPasswordException ex = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromPlainText("abcdefgh") // só letras
        );
        assertTrue(Objects.requireNonNull(ex.toProblemDetail().getDetail())
                .contains("must contain at least one letter, one number and one special"));
    }

    @Test
    void shouldCreateFromEncryptedPassword() {
        Password plain = Password.fromPlainText("Abcdef1!");
        Password encrypted = Password.fromEncrypted(plain.toString());
        assertTrue(encrypted.isEncrypted());
    }

    @Test
    void shouldThrowOnNullOrEmptyEncryptedPassword() {
        InvalidPasswordException ex1 = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromEncrypted(null)
        );
        assertTrue(Objects.requireNonNull(ex1.toProblemDetail().getDetail())
                .contains("cannot be null or empty"));

        InvalidPasswordException ex2 = assertThrows(
                InvalidPasswordException.class,
                () -> Password.fromEncrypted("   ")
        );
        assertTrue(Objects.requireNonNull(ex2.toProblemDetail().getDetail())
                .contains("cannot be null or empty"));
    }

    @Test
    void matchesShouldWorkCorrectly() {
        Password password = Password.fromPlainText("Abcdef1!");
        assertTrue(password.matches("Abcdef1!"));
        assertFalse(password.matches("WrongPass1!"));
    }

    @Test
    void equalsShouldCompareEncryptedPasswords() {
        Password p1 = Password.fromPlainText("Abcdef1!");
        Password p2 = Password.fromEncrypted(p1.getValue());
        Password p3 = Password.fromPlainText("Xyz1234!");
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }

    @Test
    void generateRandomPasswordShouldReturnUniquePasswords() {
        Set<String> generated = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            Password p = Password.generateRandomPassword();
            assertTrue(p.isEncrypted());
            generated.add(p.getValue());
        }
        assertEquals(20, generated.size(), "Generated passwords should be unique");
    }
}
