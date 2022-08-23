package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();
    User getUserById(long id);
    User saveUser(User user);
    User updateUser(User user);
    User patchRole(long userId, Collection<String> roleNames);
    User patchPassword(long userId, String oldPassword, String newPassword);
    void deleteUser(long id);
    User setTeam(long userId, String teamId);
    User removeTeam(long userId, String teamId);
    User addSpecs(long userId, Set<String> specs);
    User removeSpecs(long userId, Set<String> specs);
}
