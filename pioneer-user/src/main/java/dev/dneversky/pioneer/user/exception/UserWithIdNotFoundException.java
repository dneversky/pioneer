package dev.dneversky.pioneer.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserWithIdNotFoundException extends ResponseStatusException {

    public UserWithIdNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "User with id '" + id + "' not found.");
    }
}
