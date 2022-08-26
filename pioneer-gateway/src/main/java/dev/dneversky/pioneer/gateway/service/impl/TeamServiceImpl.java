package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.TeamGrpcImpl;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.dto.TeamBody;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.TeamService;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamGrpcImpl teamGrpcImpl;
    private final SpecServiceImpl specServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public TeamServiceImpl(TeamGrpcImpl teamGrpcImpl, SpecServiceImpl specServiceImpl, UserServiceImpl userServiceImpl) {
        this.teamGrpcImpl = teamGrpcImpl;
        this.specServiceImpl = specServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    public List<Team> getTeams() {
        return constructTeamsWithProtoTeams(teamGrpcImpl.getTeams());
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
    public Team updateTeam(Team team) {
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

    private List<Spec> getSpecsWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return specServiceImpl.getSpecsByIds(protoTeam.getSpecsIdsList());
    }

    private List<User> getUsersWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return userServiceImpl.getUsersByIds(protoTeam.getMembersIdsList());
    }

    private Team constructTeamWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        String teamId = protoTeam.getId();
        List<User> members = getUsersWithProtoTeam(protoTeam);
        List<Spec> specs = getSpecsWithProtoTeam(protoTeam);
        return new Team(teamId, members, specs);
    }

    private List<Team> constructTeamsWithProtoTeams(List<TeamServiceOuterClass.Team> protoTeams) {
        List<Team> teams = new ArrayList<>();
        for(TeamServiceOuterClass.Team protoTeam : protoTeams) {
            Team team = new Team();
            team.setId(protoTeam.getId());
            team.setSpecs(getSpecsWithProtoTeam(protoTeam));
            team.setMembers(getUsersWithProtoTeam(protoTeam));
            teams.add(team);
        }
        return teams;
    }
}
