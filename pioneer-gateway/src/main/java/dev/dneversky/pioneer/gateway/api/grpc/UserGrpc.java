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
    UserServiceOuterClass.User patchRoles(long userId, Collection<String> roleNames);
    UserServiceOuterClass.User patchPassword(long userId, String oldPassword, String newPassword);
    UserServiceOuterClass.User deleteUser(long userId);
    UserServiceOuterClass.User addTeam(long userId, String teamId);
    UserServiceOuterClass.User addSpecs(long userId, Collection<String> specsIds);
    UserServiceOuterClass.User removeTeam(long userId, String teamId);
    UserServiceOuterClass.User removeSpecs(long userId, Collection<String> specsIds);
}
