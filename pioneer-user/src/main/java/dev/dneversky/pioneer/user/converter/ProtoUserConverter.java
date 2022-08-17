package dev.dneversky.pioneer.user.converter;

import dev.dneversky.pioneer.user.entity.User;
import org.dneversky.gateway.UserServiceOuterClass;

public class ProtoUserConverter {

    public static UserServiceOuterClass.User convert(User user) {
        return UserServiceOuterClass.User.newBuilder()
                .setId(user.getId())
                .setNickname(user.getNickname())
                .setTeamId(user.getTeamId())
                .addAllSpecsIds(user.getSpecs())
                .build();
    }
}
