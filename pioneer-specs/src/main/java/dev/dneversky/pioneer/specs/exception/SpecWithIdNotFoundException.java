package dev.dneversky.pioneer.specs.exception;

public class SpecWithIdNotFoundException extends RuntimeException {
    public SpecWithIdNotFoundException(String specId) {
        super("Spec with id " + specId + " not found.");
    }
}
