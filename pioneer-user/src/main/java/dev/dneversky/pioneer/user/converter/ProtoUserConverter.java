package dev.dneversky.pioneer.user.converter;

import dev.dneversky.pioneer.user.entity.User;
import org.dneversky.gateway.UserServiceOuterClass;

import java.util.Optional;

public class ProtoUserConverter {

    public static UserServiceOuterClass.User convert(User user) {
        return UserServiceOuterClass.User.newBuilder()
                .setId(user.getId())
                .setNickname(user.getNickname())
                .setTeamId(Optional.ofNullable(user.getTeamId()).orElse(""))
                .addAllSpecsIds(user.getSpecs())
                .build();
    }
}
