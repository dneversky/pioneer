package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.impl.UserGrpcImpl;
import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.UserService;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserGrpcImpl userGrpcImpl;
    private final SpecServiceImpl specServiceImpl;

    @Autowired
    public UserServiceImpl(UserGrpcImpl userGrpcImpl, SpecServiceImpl specServiceImpl) {
        this.userGrpcImpl = userGrpcImpl;
        this.specServiceImpl = specServiceImpl;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = (List<UserServiceOuterClass.User>) userGrpcImpl.getUsers();
        for(UserServiceOuterClass.User protoUser : protoUsers) {
            users.add(constructUserWithProtoUser(protoUser));
        }
        return users;
    }

    @Override
    public User getUserById(long userId) {
        return constructUserWithProtoUser(userGrpcImpl.getUserById(userId));
    }

    public List<User> getUsersByIds(Collection<Long> ids) {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = userGrpcImpl.getProtoUsersByIds(ids);
        for(UserServiceOuterClass.User protoUser : protoUsers) {
            User user = new User();
            user.setId(protoUser.getId());
            user.setNickname(protoUser.getNickname());
            user.setSpecs(getSpecsWithProtoUser(protoUser));
            users.add(constructUserWithProtoUser(protoUser));
            users.add(user);
        }
        return users;
    }

    public User createUser(NewUser newUser) {
        return constructUserWithProtoUser(userGrpcImpl.createUser(newUser));
    }

    @Override
    public User updateUser(User user) {
        return constructUserWithProtoUser(userGrpcImpl.updateUser(user));
    }

    @Override
    public User changeRoles(long userId, Collection<String> roleNames) {
        return constructUserWithProtoUser(userGrpcImpl.changeRoles(userId, roleNames));
    }

    @Override
    public User changePassword(long userId, String oldPassword, String newPassword) {
        return constructUserWithProtoUser(userGrpcImpl.changePassword(userId, oldPassword, newPassword));
    }

    @Override
    public void deleteUser(long userId) {
        userGrpcImpl.deleteUser(userId);
    }

    @Override
    public User changeTeam(long userId, String teamId) {
        return constructUserWithProtoUser(userGrpcImpl.changeTeam(userId, teamId));
    }

    @Override
    public User changeSpecs(long userId, Collection<String> specsIds) {
        return constructUserWithProtoUser(userGrpcImpl.changeSpecs(userId, specsIds));
    }

    private User constructUserWithProtoUser(UserServiceOuterClass.User protoUser) {
        User user = new User();
        user.setId(protoUser.getId());
        user.setNickname(protoUser.getNickname());
        user.setSpecs(getSpecsWithProtoUser(protoUser));
        user.setTeam(null);
        return user;
    }

    private List<Spec> getSpecsWithProtoUser(UserServiceOuterClass.User protoUser) {
        return specServiceImpl.getSpecsByIds(protoUser.getSpecsIdsList());
    }
}
