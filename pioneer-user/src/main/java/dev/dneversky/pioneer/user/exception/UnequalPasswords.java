package dev.dneversky.pioneer.user.exception;

public class UnequalPasswords extends RuntimeException {
    public UnequalPasswords(String message) {
        super(message);
    }
}
