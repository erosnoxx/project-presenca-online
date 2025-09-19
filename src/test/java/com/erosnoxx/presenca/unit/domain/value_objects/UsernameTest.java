package com.erosnoxx.presenca.unit.domain.value_objects;

import com.erosnoxx.presenca.core.domain.exceptions.value_objects.InvalidUsernameException;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {

    @Test
    void shouldThrowIfNull() {
        assertThrows(InvalidUsernameException.class, () -> Username.of(null));
    }

    @Test
    void shouldThrowIfEmpty() {
        assertThrows(InvalidUsernameException.class, () -> Username.of(""));
        assertThrows(InvalidUsernameException.class, () -> Username.of("   "));
    }

    @Test
    void shouldThrowIfTooShort() {
        assertThrows(InvalidUsernameException.class, () -> Username.of("a.b"));
    }

    @Test
    void shouldThrowIfTooLong() {
        assertThrows(InvalidUsernameException.class, () -> Username.of("muitolongnome.sobrenome"));
    }

    @Test
    void shouldThrowIfInvalidFormat() {
        assertThrows(InvalidUsernameException.class, () -> Username.of("nome_sobrenome"));
        assertThrows(InvalidUsernameException.class, () -> Username.of("nomesobrenome"));
        assertThrows(InvalidUsernameException.class, () -> Username.of("nome.123"));
        assertThrows(InvalidUsernameException.class, () -> Username.of("nome..sobrenome"));
    }

    @Test
    void shouldConvertToLowercase() {
        Username u = Username.of("Nome.Sobrenome");
        assertEquals("nome.sobrenome", u.getValue());
    }

    @Test
    void shouldAcceptValidUsername() {
        Username u = Username.of("joao.silva");
        assertEquals("joao.silva", u.getValue());
    }
}
