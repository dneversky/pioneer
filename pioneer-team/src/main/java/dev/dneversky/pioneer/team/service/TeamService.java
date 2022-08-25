package dev.dneversky.pioneer.team.service;

import dev.dneversky.pioneer.team.entity.Team;

import java.util.Collection;
import java.util.List;

public interface TeamService {
    List<Team> getTeams();
    Team getTeamById(String teamId);
    Team saveTeam(Team team);
    Team updateTeam(Team team);
    void deleteTeam(String teamId);
    Team changeSpecs(String teamId, Collection<String> specsIds);
    Team changeMembers(String teamId, Collection<Long> membersIds);
}
