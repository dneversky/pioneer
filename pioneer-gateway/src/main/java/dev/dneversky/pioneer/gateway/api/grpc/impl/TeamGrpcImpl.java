package dev.dneversky.pioneer.gateway.api.grpc.impl;

import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.User;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.TeamServiceGrpc;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamGrpcImpl {

    @GrpcClient("team-service")
    private TeamServiceGrpc.TeamServiceBlockingStub blockingStub;

    public List<TeamServiceOuterClass.Team> getProtoTeams() {
        TeamServiceOuterClass.Teams response = blockingStub.getTeams(TeamServiceOuterClass.EmptyTeam.newBuilder().build());
        return response.getTeamsList();
    }

    public TeamServiceOuterClass.Team createTeam(Team team) {
        return blockingStub.createTeam(TeamServiceOuterClass.NewTeam.newBuilder()
                        .addAllMembersIds(team.getMembers().stream().map(User::getId).collect(Collectors.toSet()))
                        .addAllSpecsIds(team.getSpecs().stream().map(Spec::getId).collect(Collectors.toSet())).build());
    }
}
