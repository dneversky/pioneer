package dev.dneversky.pioneer.team.exception;

public class TeamWithIdNotFoundException extends RuntimeException {
    public TeamWithIdNotFoundException(String teamId) {
        super("Team with id " + teamId + " not found.");
    }
}
