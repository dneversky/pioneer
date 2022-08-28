package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.dto.TeamToCreateDto;
import dev.dneversky.pioneer.gateway.model.Team;

import java.util.Collection;

public interface TeamService {
    Collection<Team> getTeams();
    Team getTeamById(String teamId);
    Team createTeam(TeamToCreateDto teamToCreateDto);
    void deleteTeam(String teamId);
    Team addSpecs(String teamId, Collection<String> specsIds);
    Team removeSpecs(String teamId, Collection<String> specsIds);
    Team addMembers(String teamId, Collection<Long> membersIds);
    Team removeMembers(String teamId, Collection<Long> membersIds);
}
