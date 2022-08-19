package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.api.grpc.UserGrpc;
import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserGrpc userGrpc;
    private final SpecService specService;

    @Autowired
    public UserService(UserGrpc userGrpc, SpecService specService) {
        this.userGrpc = userGrpc;
        this.specService = specService;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = userGrpc.getProtoUsers();
        for(UserServiceOuterClass.User protoUser : protoUsers) {
            users.add(constructUserWithProtoUser(protoUser));
        }
        return users;
    }

    public List<User> getUsersByIds(Collection<Long> ids) {
        List<User> users = new ArrayList<>();
        List<UserServiceOuterClass.User> protoUsers = userGrpc.getProtoUsersByIds(ids);
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
        return constructUserWithProtoUser(userGrpc.createUser(newUser));
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
        return specService.getSpecsByIds(protoUser.getSpecsIdsList());
    }
}
