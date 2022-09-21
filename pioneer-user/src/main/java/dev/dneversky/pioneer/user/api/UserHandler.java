package dev.dneversky.pioneer.user.api;

import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.service.impl.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final DefaultUserService userService;

    @Autowired
    public UserHandler(DefaultUserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAllUsers(), User.class);
    }

    public Mono<ServerResponse> getUsersById(ServerRequest request) {
        Flux<String> usersId = request.bodyToFlux(String.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getUsersById(usersId), User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return userService.getUserById(request.pathVariable("userId"))
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(user, User.class)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.createUser(userMono), User.class);
    }

    public Mono<ServerResponse> updateUserById(ServerRequest request) {
        String id = request.pathVariable("userId");
        Mono<User> updatedUser = request.bodyToMono(User.class);
        return updatedUser.flatMap(u -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.updateUserById(id, u), User.class));
    }

    public Mono<ServerResponse> deleteUserById(ServerRequest request){
        return userService.deleteUserById(request.pathVariable("userId"))
                .flatMap(u -> ServerResponse.ok().body(u, User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}