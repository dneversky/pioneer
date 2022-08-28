package dev.dneversky.pioneer.user.exception;

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

    @GrpcExceptionHandler(UserWithIdNotFoundException.class)
    public StatusException handleUserWithIdNotFoundException(UserWithIdNotFoundException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.NOT_FOUND.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getMessage());
        return Status.NOT_FOUND.withDescription(e.getMessage()).asException(metadata);
    }

    @GrpcExceptionHandler(UserWithUsernameExistsException.class)
    public StatusException handleUserWithUsernameExistsException(UserWithUsernameExistsException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.FOUND.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getMessage());
        return Status.ALREADY_EXISTS.withDescription(e.getMessage()).asException(metadata);
    }

    @GrpcExceptionHandler(UnequalPasswordsException.class)
    public StatusException handleUnequalPasswordsException(UnequalPasswordsException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.FORBIDDEN.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getMessage());
        return Status.PERMISSION_DENIED.withDescription(e.getMessage()).asException(metadata);
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
