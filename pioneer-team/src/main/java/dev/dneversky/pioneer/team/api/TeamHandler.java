package dev.dneversky.pioneer.team.api;

import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.service.impl.DefaultTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TeamHandler {

    private final DefaultTeamService teamService;

    @Autowired
    public TeamHandler(DefaultTeamService teamService) {
        this.teamService = teamService;
    }

    public Mono<ServerResponse> getTeams(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(teamService.getTeams(), Team.class);
    }

    public Mono<ServerResponse> createTeam(ServerRequest request) {
        Mono<Team> teamMono = request.bodyToMono(Team.class);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(teamService.createTeam(teamMono), Team.class);
    }
}
