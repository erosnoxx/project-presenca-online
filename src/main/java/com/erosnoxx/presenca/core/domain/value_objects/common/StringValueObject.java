package com.erosnoxx.presenca.core.domain.value_objects.common;

public abstract class StringValueObject extends ValueObject<String> {

    protected StringValueObject(String value, int minLength, int maxLength) {
        super(value);
        if (value.length() < minLength || value.length() > maxLength) {
            throw createException("must be between " + minLength + " and " + maxLength + " characters"
            );
        }
    }

    @Override
    protected String validate(String value) {
        if (value == null) {
            throw createException(getClass().getSimpleName() + " cannot be null");
        }

        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw createException(getClass().getSimpleName() + " cannot be empty");
        }

        return customValidate(trimmed);
    }

    protected abstract String customValidate(String value);

    public boolean isEmpty() {
        return value == null || value.trim().isEmpty();
    }

    public int length() {
        return value != null ? value.length() : 0;
    }

    protected abstract RuntimeException createException(String message);
}
