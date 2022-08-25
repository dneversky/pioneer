package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.TeamBody;

import java.util.Collection;

public interface TeamService {
    Collection<Team> getTeams();
    Team getTeamById(String teamId);
    Team createTeam(TeamBody teamBody);
    Team updateTeam(Team team);
    void deleteTeam(String teamId);
    Team changeSpecs(Collection<String> specsIds);
    Team changeMembers(Collection<Long> membersIds);
}
