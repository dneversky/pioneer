package dev.dneversky.pioneer.team.exception;

public class KafkaCommunicationException extends RuntimeException {
    public KafkaCommunicationException(String message) {
        super(message);
    }
}
