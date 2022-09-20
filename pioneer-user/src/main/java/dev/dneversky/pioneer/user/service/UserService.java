package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

public interface UserService {
    Flux<User> getUsers();
    Flux<User> getUsersByIds(Collection<String> ids);
    Mono<User> getUserById(String id);
    Mono<User> saveUser(User user);
    Mono<User> updateUser(User user);
    Mono<User> changeRoles(String userId, Collection<String> roleNames);
    Mono<User> changePassword(String userId, String oldPassword, String newPassword);
    void deleteUser(String id);
    Mono<User> changeTeam(String userId, String teamId);
    Mono<User> changeSpecs(String userId, Set<String> specs);
}
