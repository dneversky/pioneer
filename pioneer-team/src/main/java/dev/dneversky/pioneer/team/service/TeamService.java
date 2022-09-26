package dev.dneversky.pioneer.team.service;

import dev.dneversky.pioneer.team.entity.Team;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface TeamService {
    Flux<Team> getTeams();
    Mono<Team> getTeamById(Mono<String> id);
    Mono<Object> createTeam(Mono<Team> team);
    Mono<Team> updateTeamById(String id, Team team);
    Mono<Void> deleteTeamById(Mono<String> id);
    Mono<Team> changeSpecs(String teamId, Collection<String> specsIds);
    Mono<Team> changeMembers(String teamId, Collection<String> membersIds);
}
