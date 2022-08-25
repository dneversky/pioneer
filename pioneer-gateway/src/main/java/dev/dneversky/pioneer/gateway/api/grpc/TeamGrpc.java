package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.TeamBody;
import org.dneversky.gateway.TeamServiceOuterClass;

import java.util.Collection;

public interface TeamGrpc {
    Collection<TeamServiceOuterClass.Team> getTeams();
    TeamServiceOuterClass.Team getTeamById(String teamId);
    TeamServiceOuterClass.Team createTeam(TeamBody teamBody);
    TeamServiceOuterClass.Team updateTeam(Team team);
    void deleteTeam(String teamId);
    TeamServiceOuterClass.Team changeSpecs(Collection<String> specsIds);
    TeamServiceOuterClass.Team changeMembers(Collection<Long> membersIds);
}
