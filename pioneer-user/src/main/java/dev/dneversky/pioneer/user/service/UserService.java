package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();
    List<User> getUsersByIds(Collection<Long> ids);
    User getUserById(long id);
    User saveUser(User user);
    User updateUser(User user);
    User changeRoles(long userId, Collection<String> roleNames);
    User changePassword(long userId, String oldPassword, String newPassword);
    void deleteUser(long id);
    User changeTeam(long userId, String teamId);
    User changeSpecs(long userId, Set<String> specs);
}
