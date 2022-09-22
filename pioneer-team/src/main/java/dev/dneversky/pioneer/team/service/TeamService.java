package dev.dneversky.pioneer.team.service;

import dev.dneversky.pioneer.team.entity.Team;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface TeamService {
    Mono<Team> getTeams();
    Mono<Team> getTeamById(String id);
    Mono<Team> saveTeam(Mono<Team> team);
    Mono<Team> updateTeamById(String id, Team team);
    Mono<Team> deleteTeamById(String id);
    Mono<Team> changeSpecs(String teamId, Collection<String> specsIds);
    Mono<Team> changeMembers(String teamId, Collection<String> membersIds);
}
