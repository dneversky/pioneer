package dev.dneversky.pioneer.team.config;

import dev.dneversky.pioneer.team.api.TeamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(TeamHandler handler) {
        return route(GET("/teams"), handler::getTeams)
                .andRoute(POST("/teams"), handler::createTeam);
    }
}
