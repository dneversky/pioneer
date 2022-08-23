package dev.dneversky.pioneer.user.converter;

import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.entity.UserDetails;
import org.dneversky.gateway.UserServiceOuterClass;

import java.util.stream.Collectors;

public class UserConverter {

    public static User convert(UserServiceOuterClass.NewUser protoUser) {
        User newUser = new User();
        newUser.setNickname(protoUser.getNickname());
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(protoUser.getUsername());
        userDetails.setPassword(protoUser.getPassword());
        newUser.setDetails(userDetails);
        return newUser;
    }

    public static User convert(UserServiceOuterClass.User protoUser) {
        User user = new User();
        user.setId(protoUser.getId());
        user.setNickname(user.getNickname());
        user.setTeamId(protoUser.getTeamId());
        user.setSpecs(protoUser.getSpecsIdsList().stream().map(String::valueOf).collect(Collectors.toSet()));
        return user;
    }
}
