package com.erosnoxx.presenca.core.domain.value_objects;

import com.erosnoxx.presenca.core.domain.exceptions.value_objects.InvalidNameException;
import com.erosnoxx.presenca.core.domain.value_objects.common.StringValueObject;

public class Name extends StringValueObject {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 100;

    public Name(String value) {
        super(value, MIN_LENGTH, MAX_LENGTH);
    }

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    protected String customValidate(String value) {
        return value;
    }

    @Override
    protected RuntimeException createException(String message) {
        return new InvalidNameException(message);
    }
}
