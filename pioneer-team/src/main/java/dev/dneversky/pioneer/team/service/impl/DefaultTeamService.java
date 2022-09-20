package dev.dneversky.pioneer.team.service.impl;

import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.exception.TeamWithIdNotFoundException;
import dev.dneversky.pioneer.team.repository.TeamRepository;
import dev.dneversky.pioneer.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(String teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        teamRepository.findById(team.getId()).orElseThrow(() -> new TeamWithIdNotFoundException(team.getId()));
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(String teamId) {
        Team findTeam = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
        teamRepository.delete(findTeam);
    }

    @Override
    public Team changeSpecs(String teamId, Collection<String> specsIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
        team.setSpecs(specsIds);
        return teamRepository.save(team);
    }

    @Override
    public Team changeMembers(String teamId, Collection<String> membersIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
        team.setMembers(membersIds);
        return teamRepository.save(team);
    }
}
