package com.erosnoxx.presenca.core.domain.value_objects;

import com.erosnoxx.presenca.core.domain.value_objects.common.StringValueObject;

public class Username extends StringValueObject {
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 20;

    public Username(String value) {
        super(value, MIN_LENGTH, MAX_LENGTH);
    }

    @Override
    protected String customValidate(String value) {
        return value;
    }

    @Override
    protected RuntimeException createException(String message) {
        return null;
    }
}
