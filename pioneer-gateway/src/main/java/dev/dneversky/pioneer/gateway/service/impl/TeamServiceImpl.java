package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.TeamGrpcImpl;
import dev.dneversky.pioneer.gateway.dto.TeamToCreateDto;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.service.TeamService;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamGrpcImpl teamGrpcImpl;

    @Autowired
    public TeamServiceImpl(TeamGrpcImpl teamGrpcImpl) {
        this.teamGrpcImpl = teamGrpcImpl;
    }

    public List<Team> getTeams() {
        return teamGrpcImpl.getTeams().stream()
                .map(this::constructTeamWithProtoTeam)
                .collect(Collectors.toList());
    }

    @Override
    public Team getTeamById(String teamId) {
        return constructTeamWithProtoTeam(teamGrpcImpl.getTeamById(teamId));
    }

    @Override
    public Team createTeam(TeamToCreateDto teamToCreateDto) {
        return constructTeamWithProtoTeam(teamGrpcImpl.createTeam(teamToCreateDto));
    }

    @Override
    public Team updateTeam(Team team) {
        return constructTeamWithProtoTeam(teamGrpcImpl.updateTeam(team));
    }

    @Override
    public void deleteTeam(String teamId) {
        teamGrpcImpl.deleteTeam(teamId);
    }

    @Override
    public Team changeSpecs(String teamId, Collection<String> specsIds) {
        return constructTeamWithProtoTeam(teamGrpcImpl.changeSpecs(teamId, specsIds));
    }

    @Override
    public Team changeMembers(String teamId, Collection<Long> membersIds) {
        return constructTeamWithProtoTeam(teamGrpcImpl.changeMembers(teamId, membersIds));
    }

    private Team constructTeamWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return Team.builder()
                .id(protoTeam.getId())
                .membersId(protoTeam.getMembersIdsList())
                .specsId(protoTeam.getSpecsIdsList())
                .build();
    }
}
