package dev.dneversky.pioneer.user.service;

import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.model.RelativeTeamRequest;
import dev.dneversky.pioneer.user.model.RelativeTeamResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

public interface UserService {
    Flux<User> getAllUsers();
    Mono<User> getUserById(Mono<String> id);
    Mono<User> createUser(Mono<User> userMono);  // Receives Mono for unblocking behaviour
    Mono<User> updateUserById(String id, User user);
    Mono<User> changeRoles(String userId, Collection<String> roleNames);
    Mono<User> changePassword(String userId, String oldPassword, String newPassword);
    Mono<Void> deleteUserById(String id);
    Mono<RelativeTeamResponse> changeTeam(Mono<RelativeTeamRequest> teamRequestMono);
    Mono<User> changeSpecs(String userId, Set<String> specsId);
}
