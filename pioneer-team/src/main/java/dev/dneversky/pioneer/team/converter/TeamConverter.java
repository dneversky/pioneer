package dev.dneversky.pioneer.team.converter;

import dev.dneversky.pioneer.team.entity.Team;
import org.dneversky.gateway.TeamServiceOuterClass;

public class TeamConverter {

    public static Team convert(TeamServiceOuterClass.TeamBody protoTeamBody) {
        Team team = new Team();
        team.setMembers(protoTeamBody.getMembersIdsList());
        team.setSpecs(protoTeamBody.getSpecsIdsList());
        return team;
    }

    public static Team convert(TeamServiceOuterClass.Team protoTeam) {
        Team team = new Team();
        team.setId(protoTeam.getId());
        team.setMembers(protoTeam.getMembersIdsList());
        team.setSpecs(protoTeam.getSpecsIdsList());
        return team;
    }
}
