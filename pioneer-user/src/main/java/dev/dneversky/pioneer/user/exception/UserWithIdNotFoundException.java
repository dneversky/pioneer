package dev.dneversky.pioneer.user.exception;

public class UserWithIdNotFoundException extends RuntimeException {
    public UserWithIdNotFoundException(long id) {
        super("User with id " + id + " not found.");
    }
}
