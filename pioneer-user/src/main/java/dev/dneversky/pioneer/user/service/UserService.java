package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();
    User getUserById(long id);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(long id);

    User addTeam(long userId, String teamId);
    User deleteTeam(long userId);
    User addSpecs(long userId, Set<String> specs);
    User deleteSpecs(long userId, Set<String> specs);
}
