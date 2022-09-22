package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

public interface UserService {
    Mono<User> getAllUsers();
    Mono<User> getUserById(Mono<String> id);
    Mono<User> createUser(Mono<User> userMono);  // Receives Mono for unblocking behaviour
    Mono<User> updateUserById(String id, User user);
    Mono<User> changeRoles(String userId, Collection<String> roleNames);
    Mono<User> changePassword(String userId, String oldPassword, String newPassword);
    Mono<User> deleteUserById(String id);
    Mono<User> changeTeam(String userId, String teamId);
    Mono<User> changeSpecs(String userId, Set<String> specsId);
}
