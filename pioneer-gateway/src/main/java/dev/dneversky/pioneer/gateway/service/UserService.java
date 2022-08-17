package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.api.grpc.UserGrpc;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            User user = new User();
            user.setId(protoUser.getId());
            user.setNickname(protoUser.getNickname());
            user.setSpecs(getSpecsWithProtoUser(protoUser));
            user.setTeam(null);
            users.add(user);
        }

        return users;
    }

    private Set<Spec> getSpecsWithProtoUser(UserServiceOuterClass.User protoUser) {
        return (Set<Spec>) specService.getSpecsByIds(protoUser.getSpecsIdsList());
    }
}
