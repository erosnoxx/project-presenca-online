package com.erosnoxx.presenca.core.domain.value_objects;

import com.erosnoxx.presenca.core.domain.exceptions.value_objects.InvalidUsernameException;
import com.erosnoxx.presenca.core.domain.value_objects.common.StringValueObject;

public class Username extends StringValueObject {
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 20;
    private static final String USERNAME_REGEX = "^[a-z]+\\.[a-z]+$";

    public static Username of(String username) {
        return new Username(username);
    }

    public Username(String value) {
        super(value, MIN_LENGTH, MAX_LENGTH);
    }

    @Override
    protected String customValidate(String value) {
        var lower = value.toLowerCase();
        if (!lower.matches(USERNAME_REGEX)) {
            throw createException("username must be in format 'name.lastname' with only letters");
        }

        return lower;
    }

    @Override
    protected RuntimeException createException(String message) {
        return new InvalidUsernameException(message);
    }
}
