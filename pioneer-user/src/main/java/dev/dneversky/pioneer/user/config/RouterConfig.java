package dev.dneversky.pioneer.user.config;

import dev.dneversky.pioneer.user.api.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return route(GET("/users/"), handler::getAllUsers)
                .andRoute(GET("/users"), handler::getUsersById)
                .andRoute(GET("/users/{userId}"), handler::getUserById)
                .andRoute(POST("/users"), handler::createUser)
                .andRoute(PUT("/users/{userId}"), handler::updateUserById)
                .andRoute(DELETE("/users/{userId}"), handler::deleteUserById);
    }
}
