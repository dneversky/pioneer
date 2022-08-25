package dev.dneversky.pioneer.team.exception;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.http.HttpStatus;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler(TeamWithIdNotFoundException.class)
    public StatusException handleTeamWithIdNotFoundException(TeamWithIdNotFoundException e) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("status_code", Metadata.ASCII_STRING_MARSHALLER), HttpStatus.NOT_FOUND.name());
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), e.getMessage());
        return Status.NOT_FOUND.withDescription(e.getMessage()).asException(metadata);
    }
}
