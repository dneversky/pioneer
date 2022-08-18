package dev.dneversky.pioneer.user.converter;

import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.entity.UserDetails;
import org.dneversky.gateway.UserServiceOuterClass;

public class UserConverter {

    public static User convert(UserServiceOuterClass.NewUser protoUser) {
        User newUser = new User();
        newUser.setNickname(protoUser.getNickname());
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(protoUser.getUsername());
        userDetails.setPassword(protoUser.getPassword());
        newUser.setUserDetails(userDetails);
        return newUser;
    }
}
