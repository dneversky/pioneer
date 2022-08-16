package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.model.User;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @GrpcClient("cloud-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    public List<User> getUsers() {
        return null;
    }

    private List<UserServiceOuterClass.User> getProtoUsers() {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsers(UserServiceOuterClass.EmptyUser.newBuilder().build());
        return response.getUsersList();
    }
}
