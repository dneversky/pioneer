package dev.dneversky.pioneer.gateway.exception;

import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<?> handleStatusRuntimeException(StatusRuntimeException e) {
        Metadata trailers = e.getTrailers();
        String statusCode = trailers.get(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER));
        String message = trailers.get(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER));
        return new ResponseEntity<>(message, HttpStatus.valueOf(statusCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
