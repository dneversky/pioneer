package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.api.grpc.TeamGrpc;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.User;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class TeamService {

    private final TeamGrpc teamGrpc;
    private final SpecService specService;
    private final UserService userService;

    @Autowired
    public TeamService(TeamGrpc teamGrpc, SpecService specService, UserService userService) {
        this.teamGrpc = teamGrpc;
        this.specService = specService;
        this.userService = userService;
    }

    public Collection<Team> getTeams(Collection<String> ids) {
        List<Team> teams = new ArrayList<>();
        List<TeamServiceOuterClass.Team> protoTeams = teamGrpc.getProtoTeams();
        for(TeamServiceOuterClass.Team protoTeam : protoTeams) {
            Team team = new Team();
            team.setSpecs(getSpecsWithProtoTeam(protoTeam));
            team.setMembers(getUsersWithProtoTeam(protoTeam));
            teams.add(team);
        }
        return teams;
    }

    public Team createTeam(Team team) {
        TeamServiceOuterClass.Team newProtoTeam = teamGrpc.createTeam(team);
        Team newTeam = new Team();
        newTeam.setId(newProtoTeam.getId());
        newTeam.setMembers(getUsersWithProtoTeam(newProtoTeam));
        newTeam.setSpecs(getSpecsWithProtoTeam(newProtoTeam));
        return newTeam;
    }

    private Set<Spec> getSpecsWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return (Set<Spec>) specService.getSpecsByIds(protoTeam.getSpecsIdsList());
    }

    private Set<User> getUsersWithProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return (Set<User>) userService.getUsersByIds(protoTeam.getMembersIdsList());
    }
}
