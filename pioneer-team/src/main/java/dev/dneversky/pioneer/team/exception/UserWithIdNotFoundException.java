package dev.dneversky.pioneer.team.exception;

public class UserWithIdNotFoundException extends RuntimeException {
    public UserWithIdNotFoundException(String message) {
        super(message);
    }
}
