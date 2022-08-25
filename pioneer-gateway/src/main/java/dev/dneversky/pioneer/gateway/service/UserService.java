package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.dto.UpdateUserDto;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.dto.UserBody;

import java.util.Collection;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(long userId);
    User createUser(UserBody userBody);
    User updateUser(UpdateUserDto user);
    User changeRoles(long userId, Collection<String> roleNames);
    User changePassword(long userId, String oldPassword, String newPassword);
    void deleteUser(long userId);
    User changeTeam(long userId, String teamId);
    User changeSpecs(long userId, Collection<String> specsIds);
}
