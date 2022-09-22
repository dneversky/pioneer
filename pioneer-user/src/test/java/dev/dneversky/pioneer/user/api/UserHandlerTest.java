package dev.dneversky.pioneer.user.api;

import dev.dneversky.pioneer.user.config.RouterConfig;
import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterConfig.class, UserHandler.class})
@WebFluxTest
class UserHandlerTest {

    @MockBean
    DefaultUserService userService;

    @Autowired
    WebTestClient testClient;

    @Test
    void getAllUsersTest() {
        User[] users = {
                User.builder().nickname("Alex Wex").build(),
                User.builder().nickname("Peter Parker").build(),
                User.builder().nickname("Alice London").build()
        };
        when(userService.getAllUsers()).thenReturn(Flux.just(users).next());
        testClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .contains(users);
    }

    @Test
    void getUserByIdTest() {
        User[] users = {
                User.builder().id("1").build(),
                User.builder().id("2").build(),
                User.builder().id("3").build()
        };
        when(userService.getUserById(any())).thenReturn(Mono.just(users[1]));
        testClient.get().uri("/users/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class).isEqualTo(users[1]);
    }

    @Test
    void createUser() {
        User unsavedUser = User.builder().nickname("Alex Wex").build();
        User savedUser = User.builder().id("1").nickname("Alex Wex").build();
        when(userService.createUser(any())).thenReturn(Mono.just(savedUser));
        testClient.post().uri("/users")
                .bodyValue(unsavedUser)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(User.class)
                .isEqualTo(savedUser);
    }

    @Test
    void updateUser() {
        User userToUpdate = User.builder().nickname("Alex Wex").build();
        User updatedUser = User.builder().id("1").nickname(userToUpdate.getNickname()).build();
        when(userService.updateUserById("1", userToUpdate)).thenReturn(Mono.just(updatedUser));
        testClient.put().uri("/users/1")
                .bodyValue(userToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(updatedUser);
    }

    @Test
    void deleteUser() {
        when(userService.deleteUserById("1")).thenReturn(Mono.empty());
        testClient.delete().uri("/users/1")
                .exchange()
                .expectStatus().isNotFound();
    }
}
