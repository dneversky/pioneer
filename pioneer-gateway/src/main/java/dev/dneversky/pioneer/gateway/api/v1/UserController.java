package dev.dneversky.pioneer.gateway.api.v1;

import dev.dneversky.pioneer.gateway.model.NewUser;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody NewUser newUser) {
        return userServiceImpl.createUser(newUser);
    }
}
