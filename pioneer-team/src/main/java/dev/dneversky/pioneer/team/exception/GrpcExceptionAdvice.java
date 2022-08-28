package dev.dneversky.pioneer.team.exception;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler(TeamWithIdNotFoundException.class)
    public StatusException handleTeamWithIdNotFoundException(TeamWithIdNotFoundException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.NOT_FOUND.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getMessage());
        return Status.NOT_FOUND.withDescription(e.getMessage()).asException(metadata);
    }

    @GrpcExceptionHandler(ConstraintViolationException.class)
    public StatusException handleConstraintViolationException(ConstraintViolationException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.BAD_REQUEST.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(",")));
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asException(metadata);
    }
}
