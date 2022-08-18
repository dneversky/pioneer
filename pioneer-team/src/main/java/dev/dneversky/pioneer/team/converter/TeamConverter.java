package dev.dneversky.pioneer.team.converter;

import dev.dneversky.pioneer.team.entity.Team;
import org.dneversky.gateway.TeamServiceOuterClass;

public class TeamConverter {

    public static Team convert(TeamServiceOuterClass.NewTeam protoNewTeam) {
        Team team = new Team();
        team.setMembers(protoNewTeam.getMembersIdsList());
        team.setSpecs(protoNewTeam.getSpecsIdsList());
        return team;
    }
}
