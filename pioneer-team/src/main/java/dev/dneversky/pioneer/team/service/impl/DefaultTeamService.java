package dev.dneversky.pioneer.team.service.impl;

import dev.dneversky.pioneer.team.MessageModel;
import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.exception.TeamWithIdNotFoundException;
import dev.dneversky.pioneer.team.repository.TeamRepository;
import dev.dneversky.pioneer.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Collection;
import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public DefaultTeamService(TeamRepository teamRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.teamRepository = teamRepository;
        this.kafkaTemplate = kafkaTemplate;
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
        Team savedTeam = teamRepository.save(team);
        for (Long memberId : team.getMembers()) {
            System.out.println("Sending member with id: " + memberId);
            kafkaTemplate.send("topic.user", new MessageModel(memberId, savedTeam.getId()));
        }
        return savedTeam;
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
    public Team changeMembers(String teamId, Collection<Long> membersIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamWithIdNotFoundException(teamId));
        for (Long memberId : membersIds) {
            ListenableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send("topic.user", new MessageModel(memberId, teamId));

            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    System.out.println("Sent message=[" + memberId +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=["
                            + memberId + "] due to : " + ex.getMessage());
                }
            });
        }
        team.setMembers(membersIds);
        return teamRepository.save(team);
    }
}
