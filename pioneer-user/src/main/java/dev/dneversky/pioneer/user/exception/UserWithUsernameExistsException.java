package dev.dneversky.pioneer.user.exception;

public class UserWithUsernameExistsException extends RuntimeException {
    public UserWithUsernameExistsException(String username) {
        super("User with username " + username + " already exists.");
    }
}
