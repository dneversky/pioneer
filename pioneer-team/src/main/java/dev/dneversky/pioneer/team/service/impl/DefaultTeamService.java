package dev.dneversky.pioneer.team.service.impl;

import dev.dneversky.pioneer.team.api.UserWebClient;
import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.entity.User;
import dev.dneversky.pioneer.team.repository.TeamRepository;
import dev.dneversky.pioneer.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Slf4j
@Service
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;
    private final UserWebClient userWebClient;

    @Autowired
    public DefaultTeamService(TeamRepository teamRepository, UserWebClient userWebClient) {
        this.teamRepository = teamRepository;
        this.userWebClient = userWebClient;
    }

    @Override
    public Mono<Team> getTeams() {
        return teamRepository.findAll().next();
    }

    @Override
    public Mono<Team> getTeamById(String id) {
        return teamRepository.findById(id);
    }

    @Override
    public Mono<Team> saveTeam(Mono<Team> team) {
        return team.flatMap(e -> getUserById(e.getMembers().get(0)))
                .flatMap(e -> teamRepository.saveAll(team).next())
                .switchIfEmpty(Mono.error(new Exception()));
    }

    @Override
    public Mono<Team> updateTeamById(String id, Team team) {
        teamRepository.findById(team.getId());
        return teamRepository.save(team);
    }

    @Override
    public Mono<Team> deleteTeamById(String id) {
        return null;
    }

    @Override
    public Mono<Team> changeSpecs(String teamId, Collection<String> specsIds) {
        return null;
    }

    @Override
    public Mono<Team> changeMembers(String teamId, Collection<String> membersIds) {
        return null;
    }

    //    @Override
//    public Mono<Team> deleteTeam(String teamId) {
//        Team findTeam = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
//        teamRepository.delete(findTeam);
//    }
//
//    @Override
//    public Mono<Team> changeSpecs(String teamId, Collection<String> specsIds) {
//        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
//        team.setSpecs(specsIds);
//        return teamRepository.save(team);
//    }
//
//    @Override
//    public Mono<Team> changeMembers(String teamId, Collection<String> membersIds) {
//        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
//        team.setMembers(membersIds);
//        return teamRepository.save(team);
//    }

    private Mono<User> getUserById(String userId) {
        return userWebClient.getUserById(userId);
    }
}
