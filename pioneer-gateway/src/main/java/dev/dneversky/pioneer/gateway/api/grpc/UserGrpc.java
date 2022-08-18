package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.model.NewUser;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserGrpc {

    @GrpcClient("cloud-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    public List<UserServiceOuterClass.User> getProtoUsers() {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsers(UserServiceOuterClass.EmptyUser.newBuilder().build());
        return response.getUsersList();
    }

    public List<UserServiceOuterClass.User> getProtoUsersByIds(Collection<Long> userIds) {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsersByIds(UserServiceOuterClass.UsersIds.newBuilder().addAllIds(userIds).build());
        return response.getUsersList();
    }

    public UserServiceOuterClass.User createUser(NewUser newUser) {
        return serviceBlockingStub.createUser(UserServiceOuterClass.NewUser.newBuilder()
                        .setNickname(newUser.getNickname())
                        .setUsername(newUser.getUsername())
                        .setPassword(newUser.getPassword()).build());
    }
}
