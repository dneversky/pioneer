package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.impl.TeamGrpcImpl;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl {

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
        List<Team> teams = new ArrayList<>();
        List<TeamServiceOuterClass.Team> protoTeams = teamGrpcImpl.getProtoTeams();
        for(TeamServiceOuterClass.Team protoTeam : protoTeams) {
            Team team = new Team();
            team.setSpecs(getSpecsWithProtoTeam(protoTeam));
            team.setMembers(getUsersWithProtoTeam(protoTeam));
            teams.add(team);
        }
        return teams;
    }

    public Team createTeam(Team team) {
        TeamServiceOuterClass.Team newProtoTeam = teamGrpcImpl.createTeam(team);
        Team newTeam = new Team();
        newTeam.setId(newProtoTeam.getId());
        newTeam.setMembers(getUsersWithProtoTeam(newProtoTeam));
        newTeam.setSpecs(getSpecsWithProtoTeam(newProtoTeam));
        return newTeam;
    }

    private List<Spec> getSpecsWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return specServiceImpl.getSpecsByIds(protoTeam.getSpecsIdsList());
    }

    private List<User> getUsersWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return userServiceImpl.getUsersByIds(protoTeam.getMembersIdsList());
    }
}
