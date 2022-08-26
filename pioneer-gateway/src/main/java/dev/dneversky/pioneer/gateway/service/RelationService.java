package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.model.Team;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.impl.SpecServiceImpl;
import dev.dneversky.pioneer.gateway.service.impl.TeamServiceImpl;
import dev.dneversky.pioneer.gateway.service.impl.UserServiceImpl;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {

    private UserServiceImpl userService;
    private TeamServiceImpl teamService;
    private SpecServiceImpl specService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void setTeamService(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    public void setSpecService(SpecServiceImpl specService) {
        this.specService = specService;
    }

    public Team getTeamFromProtoUser(UserServiceOuterClass.User protoUser) {
        return teamService.getTeamById(protoUser.getTeamId());
    }

    public List<Spec> getSpecsFromProtoUser(UserServiceOuterClass.User protoUser) {
        return specService.getSpecsByIds(protoUser.getSpecsIdsList());
    }

    public List<Spec> getSpecsFromProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return specService.getSpecsByIds(protoTeam.getSpecsIdsList());
    }

    public List<User> getUsersFromProtoTeam(TeamServiceOuterClass.Team protoTeam) {
        return userService.getUsersByIds(protoTeam.getMembersIdsList());
    }
}
