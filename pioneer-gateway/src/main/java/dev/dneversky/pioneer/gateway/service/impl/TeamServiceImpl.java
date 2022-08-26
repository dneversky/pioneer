package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.TeamGrpcImpl;
import dev.dneversky.pioneer.gateway.dto.TeamBody;
import dev.dneversky.pioneer.gateway.dto.UpdateTeamDto;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.service.RelationService;
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
    private final RelationService relationService;

    @Autowired
    public TeamServiceImpl(TeamGrpcImpl teamGrpcImpl, RelationService relationService) {
        this.teamGrpcImpl = teamGrpcImpl;
        this.relationService = relationService;
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
    public Team createTeam(TeamBody teamBody) {
        return constructTeamWithProtoTeam(teamGrpcImpl.createTeam(teamBody));
    }

    @Override
    public Team updateTeam(UpdateTeamDto team) {
        return constructTeamWithProtoTeam(teamGrpcImpl.updateTeam(team));
    }

    @Override
    public void deleteTeam(String teamId) {
        teamGrpcImpl.deleteTeam(teamId);
    }

    @Override
    public Team changeSpecs(Collection<String> specsIds) {
        return constructTeamWithProtoTeam(teamGrpcImpl.changeSpecs(specsIds));
    }

    @Override
    public Team changeMembers(Collection<Long> membersIds) {
        return constructTeamWithProtoTeam(teamGrpcImpl.changeMembers(membersIds));
    }

    private Team constructTeamWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return Team.builder()
                .id(protoTeam.getId())
                .members(relationService.getUsersFromProtoTeam(protoTeam))
                .specs(relationService.getSpecsFromProtoTeam(protoTeam))
                .build();
    }
}
