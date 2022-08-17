package dev.dneversky.pioneer.gateway.api.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGrpc {

    @GrpcClient("cloud-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    private List<UserServiceOuterClass.User> getProtoUsers() {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsers(UserServiceOuterClass.EmptyUser.newBuilder().build());
        return response.getUsersList();
    }
}
