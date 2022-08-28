package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.dto.TeamToCreateDto;
import dev.dneversky.pioneer.gateway.model.Team;

import java.util.Collection;

public interface TeamService {
    Collection<Team> getTeams();
    Team getTeamById(String teamId);
    Team createTeam(TeamToCreateDto teamToCreateDto);
    Team updateTeam(Team team);
    void deleteTeam(String teamId);
    Team changeSpecs(String teamId, Collection<String> specsIds);
    Team changeMembers(String teamId, Collection<Long> membersIds);
}
