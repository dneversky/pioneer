package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.dto.TeamToCreateDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.TeamServiceGrpc;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class TeamGrpcImpl {

    @GrpcClient("team-service")
    private TeamServiceGrpc.TeamServiceBlockingStub blockingStub;

    public List<TeamServiceOuterClass.Team> getTeams() {
        TeamServiceOuterClass.Teams response = blockingStub.getTeams(TeamServiceOuterClass.EmptyTeam.newBuilder().build());
        return response.getTeamsList();
    }

    public TeamServiceOuterClass.Team getTeamById(String teamId) {
        return blockingStub.getTeamById(TeamServiceOuterClass.TeamId.newBuilder().setId(teamId).build());
    }

    public TeamServiceOuterClass.Team createTeam(TeamToCreateDto teamToCreateDto) {
        return blockingStub.createTeam(TeamServiceOuterClass.TeamBody.newBuilder()
                        .addAllMembersIds(Collections.singleton(teamToCreateDto.getMemberId()))
                        .addAllSpecsIds(teamToCreateDto.getSpecsIds()).build());
    }

    public void deleteTeam(String teamId) {
        blockingStub.deleteTeam(TeamServiceOuterClass.TeamId.newBuilder().setId(teamId).build());
    }

    public TeamServiceOuterClass.Team addSpecs(String teamId, Collection<String> specsIds) {
        return blockingStub.addSpecs(TeamServiceOuterClass.TeamSpecs.newBuilder()
                .setTeamId(teamId)
                .addAllSpecsIds(specsIds)
                .build());
    }

    public TeamServiceOuterClass.Team removeSpecs(String teamId, Collection<String> specsIds) {
        return blockingStub.addSpecs(TeamServiceOuterClass.TeamSpecs.newBuilder()
                .setTeamId(teamId)
                .addAllSpecsIds(specsIds)
                .build());
    }

    public TeamServiceOuterClass.Team addMembers(String teamId, Collection<Long> membersIds) {
        return blockingStub.addMembers(TeamServiceOuterClass.TeamMembers.newBuilder()
                .setTeamId(teamId)
                .addAllMembersIds(membersIds)
                .build());
    }

    public TeamServiceOuterClass.Team removeMembers(String teamId, Collection<Long> membersIds) {
        return blockingStub.removeMembers(TeamServiceOuterClass.TeamMembers.newBuilder()
                .setTeamId(teamId)
                .addAllMembersIds(membersIds)
                .build());
    }
}
