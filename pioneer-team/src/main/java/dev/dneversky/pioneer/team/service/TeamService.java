package dev.dneversky.pioneer.team.service;

import dev.dneversky.pioneer.team.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findTeams();
    Team findTeamById(String teamId);
    Team updateTeam(Team team);
    Team saveTeam(Team team);
    void deleteTeam(String teamId);
}
