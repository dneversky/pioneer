package dev.dneversky.pioneer.user.exception;

public class UnequalPasswordsException extends RuntimeException {
    public UnequalPasswordsException(String message) {
        super(message);
    }
}
