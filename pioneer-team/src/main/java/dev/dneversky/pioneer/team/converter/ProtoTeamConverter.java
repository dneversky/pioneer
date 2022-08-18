package dev.dneversky.pioneer.team.converter;

import dev.dneversky.pioneer.team.entity.Team;
import org.dneversky.gateway.TeamServiceOuterClass;

public class ProtoTeamConverter {

    public static TeamServiceOuterClass.Team convert(Team team) {
        return TeamServiceOuterClass.Team.newBuilder()
                .setId(team.getId())
                .addAllMembersIds(team.getMembers())
                .addAllSpecsIds(team.getSpecs())
                .build();
    }
}
