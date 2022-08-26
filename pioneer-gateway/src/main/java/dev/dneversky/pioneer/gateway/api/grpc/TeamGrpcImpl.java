package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.dto.TeamBody;
import dev.dneversky.pioneer.gateway.dto.UpdateTeamDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.TeamServiceGrpc;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public TeamServiceOuterClass.Team createTeam(TeamBody teamBody) {
        return blockingStub.createTeam(TeamServiceOuterClass.TeamBody.newBuilder()
                        .addAllMembersIds(teamBody.getMembersIds())
                        .addAllSpecsIds(teamBody.getSpecsIds()).build());
    }

    public TeamServiceOuterClass.Team updateTeam(UpdateTeamDto team) {
        return blockingStub.updateTeam(TeamServiceOuterClass.Team.newBuilder()
                        .setId(team.getId())
                        .addAllMembersIds(team.getMembersIds())
                        .addAllSpecsIds(team.getSpecsIds()).build());
    }

    public void deleteTeam(String teamId) {
        blockingStub.deleteTeam(TeamServiceOuterClass.TeamId.newBuilder().setId(teamId).build());
    }

    public TeamServiceOuterClass.Team changeSpecs(Collection<String> specsIds) {
        return blockingStub.changeSpecs(TeamServiceOuterClass.TeamSpecs.newBuilder().addAllSpecsIds(specsIds).build());
    }

    public TeamServiceOuterClass.Team changeMembers(Collection<Long> membersIds) {
        return blockingStub.changeMembers(TeamServiceOuterClass.TeamMembers.newBuilder().addAllMembersIds(membersIds).build());
    }
}
