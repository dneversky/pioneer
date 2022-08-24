package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.UserServiceOuterClass;

import java.util.Collection;

public interface UserGrpc {
    Collection<UserServiceOuterClass.User> getUsers();
    UserServiceOuterClass.User getUserById(long userId);
    UserServiceOuterClass.User createUser(NewUser newUser);
    UserServiceOuterClass.User updateUser(User user);
    UserServiceOuterClass.User changeRoles(long userId, Collection<String> roleNames);
    UserServiceOuterClass.User changePassword(long userId, String oldPassword, String newPassword);
    UserServiceOuterClass.User deleteUser(long userId);
    UserServiceOuterClass.User changeTeam(long userId, String teamId);
    UserServiceOuterClass.User changeSpecs(long userId, Collection<String> specsIds);
}
