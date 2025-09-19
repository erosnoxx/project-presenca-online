package com.erosnoxx.presenca.core.domain.checkers;


public interface UserChecker {
    boolean isUsernameUnique(String username);
    boolean adminExists();
}
