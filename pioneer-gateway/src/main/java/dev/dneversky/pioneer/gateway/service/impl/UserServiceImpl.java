package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.UserGrpcImpl;
import dev.dneversky.pioneer.gateway.dto.UserToCreateDto;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.UserService;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserGrpcImpl userGrpcImpl;

    public UserServiceImpl(UserGrpcImpl userGrpcImpl) {
        this.userGrpcImpl = userGrpcImpl;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = (List<UserServiceOuterClass.User>) userGrpcImpl.getUsers();
        for(UserServiceOuterClass.User protoUser : protoUsers) {
            users.add(constructUserFromProtoUser(protoUser));
        }
        return users;
    }

    @Override
    public User getUserById(long userId) {
        return constructUserFromProtoUser(userGrpcImpl.getUserById(userId));
    }

    public List<User> getUsersByIds(Collection<Long> ids) {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = userGrpcImpl.getProtoUsersByIds(ids);
        for(UserServiceOuterClass.User protoUser : protoUsers) {
            users.add(constructUserFromProtoUser(protoUser));
        }
        return users;
    }

    public User createUser(UserToCreateDto userToCreateDto) {
        return constructUserFromProtoUser(userGrpcImpl.createUser(userToCreateDto));
    }

    @Override
    public User updateUser(User user) {
        return constructUserFromProtoUser(userGrpcImpl.updateUser(user));
    }

    @Override
    public User changeRoles(long userId, Collection<String> roleNames) {
        return constructUserFromProtoUser(userGrpcImpl.changeRoles(userId, roleNames));
    }

    @Override
    public User changePassword(long userId, String oldPassword, String newPassword) {
        return constructUserFromProtoUser(userGrpcImpl.changePassword(userId, oldPassword, newPassword));
    }

    @Override
    public void deleteUser(long userId) {
        userGrpcImpl.deleteUser(userId);
    }

    @Override
    public User changeTeam(long userId, String teamId) {
        return constructUserFromProtoUser(userGrpcImpl.changeTeam(userId, teamId));
    }

    @Override
    public User changeSpecs(long userId, Collection<String> specsIds) {
        return constructUserFromProtoUser(userGrpcImpl.changeSpecs(userId, specsIds));
    }

    private User constructUserFromProtoUser(UserServiceOuterClass.User protoUser) {
        return User.builder()
                .id(protoUser.getId())
                .nickname(protoUser.getNickname())
                .teamId(protoUser.getTeamId())
                .specsId(protoUser.getSpecsIdsList())
                .build();
    }
}
