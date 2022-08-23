package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.UserServiceOuterClass;

import java.util.Collection;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(long userId);
    User createUser(NewUser newUser);
    User updateUser(User user);
    User patchRoles(long userId, Collection<String> roleNames);
    User patchPassword(long userId, String oldPassword, String newPassword);
    User deleteUser(long userId);
    User addTeam(long userId, String teamId);
    User addSpecs(long userId, Collection<String> specsIds);
    User removeTeam(long userId, String teamId);
    User removeSpecs(long userId, Collection<String> specsIds);
}
