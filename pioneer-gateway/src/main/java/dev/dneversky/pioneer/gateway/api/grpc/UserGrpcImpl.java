package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.dto.UserToCreateDto;
import dev.dneversky.pioneer.gateway.model.User;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserGrpcImpl {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    public List<UserServiceOuterClass.User> getProtoUsersByIds(Collection<Long> userIds) {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsersByIds(UserServiceOuterClass.UserIds.newBuilder().addAllIds(userIds).build());
        return response.getUsersList();
    }

    public Collection<UserServiceOuterClass.User> getUsers() {
        UserServiceOuterClass.Users response = serviceBlockingStub
                .getUsers(UserServiceOuterClass.EmptyUser.newBuilder().build());
        return response.getUsersList();
    }

    public UserServiceOuterClass.User getUserById(long userId) {
        return serviceBlockingStub.getUserById(UserServiceOuterClass.UserId.newBuilder().setId(userId).build());
    }

    public UserServiceOuterClass.User createUser(UserToCreateDto userToCreateDto) {
        return serviceBlockingStub.createUser(UserServiceOuterClass.NewUser.newBuilder()
                        .setNickname(userToCreateDto.getNickname())
                        .setUsername(userToCreateDto.getUsername())
                        .setPassword(userToCreateDto.getPassword()).build());
    }

    public UserServiceOuterClass.User updateUser(User user) {
        return serviceBlockingStub.updateUser(UserServiceOuterClass.User.newBuilder()
                        .setId(user.getId())
                        .setNickname(user.getNickname())
                        .setTeamId(Optional.ofNullable(user.getTeamId()).orElse(""))
                        .addAllSpecsIds(user.getSpecsId()).build());
    }

    public UserServiceOuterClass.User changeRoles(long userId, Collection<String> roleNames) {
        return serviceBlockingStub.changeRoles(UserServiceOuterClass.UserRole.newBuilder()
                .setUserId(userId).addAllRoleName(roleNames).build());
    }

    public UserServiceOuterClass.User changePassword(long userId, String oldPassword, String newPassword) {
        return serviceBlockingStub.changePassword(UserServiceOuterClass.UserPassword.newBuilder()
                .setUserId(userId).setOldPassword(oldPassword).setNewPassword(newPassword).build());
    }

    public UserServiceOuterClass.EmptyUser deleteUser(long userId) {
        return serviceBlockingStub.deleteUser(UserServiceOuterClass.UserId.newBuilder().setId(userId).build());
    }

    public UserServiceOuterClass.User changeTeam(long userId, String teamId) {
        return serviceBlockingStub.changeTeam(UserServiceOuterClass.UserTeamIds.newBuilder()
                .setUserId(userId).setTeamId(teamId).build());
    }

    public UserServiceOuterClass.User changeSpecs(long userId, Collection<String> specsIds) {
        return serviceBlockingStub.changeSpecs(UserServiceOuterClass.UserSpecsIds.newBuilder()
                .setUserId(userId).addAllSpecsIds(specsIds).build());
    }
}
