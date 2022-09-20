package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();
    List<User> getUsersByIds(Collection<String> ids);
    User getUserById(String id);
    User saveUser(User user);
    User updateUser(User user);
    User changeRoles(String userId, Collection<String> roleNames);
    User changePassword(String userId, String oldPassword, String newPassword);
    void deleteUser(String id);
    User changeTeam(String userId, String teamId);
    User changeSpecs(String userId, Set<String> specs);
}
