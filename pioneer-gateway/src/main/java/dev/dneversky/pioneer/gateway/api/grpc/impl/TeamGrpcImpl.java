package dev.dneversky.pioneer.gateway.api.grpc.impl;

import dev.dneversky.pioneer.gateway.api.grpc.TeamGrpc;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.TeamBody;
import dev.dneversky.pioneer.gateway.model.User;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.TeamServiceGrpc;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamGrpcImpl implements TeamGrpc {

    @GrpcClient("team-service")
    private TeamServiceGrpc.TeamServiceBlockingStub blockingStub;

    @Override
    public List<TeamServiceOuterClass.Team> getTeams() {
        TeamServiceOuterClass.Teams response = blockingStub.getTeams(TeamServiceOuterClass.EmptyTeam.newBuilder().build());
        return response.getTeamsList();
    }

    @Override
    public TeamServiceOuterClass.Team getTeamById(String teamId) {
        return blockingStub.getTeamById(TeamServiceOuterClass.TeamId.newBuilder().setId(teamId).build());
    }

    @Override
    public TeamServiceOuterClass.Team createTeam(TeamBody teamBody) {
        return blockingStub.createTeam(TeamServiceOuterClass.TeamBody.newBuilder()
                        .addAllMembersIds(teamBody.getMembersIds())
                        .addAllSpecsIds(teamBody.getSpecsIds()).build());
    }

    @Override
    public TeamServiceOuterClass.Team updateTeam(Team team) {
        return blockingStub.updateTeam(TeamServiceOuterClass.Team.newBuilder()
                .addAllMembersIds(team.getMembers().stream().map(User::getId).collect(Collectors.toList()))
                .addAllSpecsIds(team.getSpecs().stream().map(Spec::getId).collect(Collectors.toList())).build());
    }

    @Override
    public void deleteTeam(String teamId) {
        blockingStub.deleteTeam(TeamServiceOuterClass.TeamId.newBuilder().setId(teamId).build());
    }

    @Override
    public TeamServiceOuterClass.Team changeSpecs(Collection<String> specsIds) {
        return blockingStub.changeSpecs(TeamServiceOuterClass.TeamSpecs.newBuilder().addAllSpecsIds(specsIds).build());
    }

    @Override
    public TeamServiceOuterClass.Team changeMembers(Collection<Long> membersIds) {
        return blockingStub.changeMembers(TeamServiceOuterClass.TeamMembers.newBuilder().addAllMembersIds(membersIds).build());
    }
}
