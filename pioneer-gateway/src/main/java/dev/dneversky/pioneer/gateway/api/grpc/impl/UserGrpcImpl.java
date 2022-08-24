package dev.dneversky.pioneer.gateway.api.grpc.impl;

import dev.dneversky.pioneer.gateway.api.grpc.UserGrpc;
import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.User;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserGrpcImpl implements UserGrpc {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    public List<UserServiceOuterClass.User> getProtoUsersByIds(Collection<Long> userIds) {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsersByIds(UserServiceOuterClass.UserIds.newBuilder().addAllIds(userIds).build());
        return response.getUsersList();
    }

    @Override
    public Collection<UserServiceOuterClass.User> getUsers() {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsers(UserServiceOuterClass.EmptyUser.newBuilder().build());
        return response.getUsersList();
    }

    @Override
    public UserServiceOuterClass.User getUserById(long userId) {
        return serviceBlockingStub.getUserById(UserServiceOuterClass.UserId.newBuilder().build());
    }

    @Override
    public UserServiceOuterClass.User createUser(NewUser newUser) {
        return serviceBlockingStub.createUser(UserServiceOuterClass.NewUser.newBuilder()
                        .setNickname(newUser.getNickname())
                        .setUsername(newUser.getUsername())
                        .setPassword(newUser.getPassword()).build());
    }

    @Override
    public UserServiceOuterClass.User updateUser(User user) {
        return serviceBlockingStub.updateUser(UserServiceOuterClass.User.newBuilder()
                        .setId(user.getId())
                        .setNickname(user.getNickname())
                        .setTeamId(user.getTeam().getId())
                        .addAllSpecsIds(user.getSpecs().stream().map(Spec::getId).collect(Collectors.toSet())).build());
    }

    @Override
    public UserServiceOuterClass.User changeRoles(long userId, Collection<String> roleNames) {
        return serviceBlockingStub.changeRoles(UserServiceOuterClass.UserRole.newBuilder()
                .setUserId(userId).addAllRoleName(roleNames).build());
    }

    @Override
    public UserServiceOuterClass.User changePassword(long userId, String oldPassword, String newPassword) {
        return serviceBlockingStub.changePassword(UserServiceOuterClass.UserPassword.newBuilder()
                .setUserId(userId).setOldPassword(oldPassword).setNewPassword(newPassword).build());
    }

    @Override
    public UserServiceOuterClass.User deleteUser(long userId) {
        return serviceBlockingStub.deleteUser(UserServiceOuterClass.UserId.newBuilder().setId(userId).build());
    }

    @Override
    public UserServiceOuterClass.User changeTeam(long userId, String teamId) {
        return serviceBlockingStub.changeTeam(UserServiceOuterClass.UserTeamIds.newBuilder()
                .setUserId(userId).setTeamId(teamId).build());
    }

    @Override
    public UserServiceOuterClass.User changeSpecs(long userId, Collection<String> specsIds) {
        return serviceBlockingStub.changeSpecs(UserServiceOuterClass.UserSpecsIds.newBuilder()
                .setUserId(userId).addAllSpecsIds(specsIds).build());
    }
}
