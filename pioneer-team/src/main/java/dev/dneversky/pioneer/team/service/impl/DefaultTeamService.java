package dev.dneversky.pioneer.team.service.impl;

import dev.dneversky.pioneer.team.api.UserWebClient;
import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.exception.UserWithIdNotFoundException;
import dev.dneversky.pioneer.team.model.RelateTeamRequest;
import dev.dneversky.pioneer.team.repository.TeamRepository;
import dev.dneversky.pioneer.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Flux<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Mono<Team> getTeamById(Mono<String> id) {
        return teamRepository.findById(id);
    }

    @Override
    public Mono<Object> createTeam(Mono<Team> teamMono) {
        return sendTeamToUsers(teamMono)
                .collectList()
                .handle((userList, sink) -> {
                    if(userList.size() == 0) {
                        sink.error(new UserWithIdNotFoundException("Users not found"));
                    } else {
                        userList.forEach(e -> teamMono.map(r -> r.getMembers().add(e)));
                        sink.next(teamRepository.saveAll(teamMono));
                    }
                })
                .onErrorReturn(Mono.empty());
    }

    @Override
    public Mono<Team> updateTeamById(String id, Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Mono<Void> deleteTeamById(Mono<String> id) {
        return teamRepository.deleteById(id);
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

    private Flux<String> sendTeamToUsers(Mono<Team> teamMono) {
        Mono<RelateTeamRequest> requestMono = teamMono.flatMap(e -> {
            RelateTeamRequest request = new RelateTeamRequest();
            request.setUsersId(e.getMembers());
            request.setTeamId(e.getId());
            return Mono.just(request);
        });
        return userWebClient.getUsersById(requestMono);
    }
}
