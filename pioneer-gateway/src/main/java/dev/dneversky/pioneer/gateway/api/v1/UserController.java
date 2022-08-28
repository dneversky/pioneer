package dev.dneversky.pioneer.gateway.api.v1;

import dev.dneversky.pioneer.gateway.dto.PasswordToChangeDto;
import dev.dneversky.pioneer.gateway.dto.UserToCreateDto;
import dev.dneversky.pioneer.gateway.model.User;
import dev.dneversky.pioneer.gateway.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/{userId}")
    public User getUsers(@PathVariable long userId) {
        return userServiceImpl.getUserById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody @Valid UserToCreateDto userToCreateDto) {
        return userServiceImpl.createUser(userToCreateDto);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        return userServiceImpl.updateUser(user);
    }

    @PatchMapping("/{userId}/password")
    public User patchPassword(@PathVariable long userId, @RequestBody @Valid PasswordToChangeDto passwordToChangeDto) {
        return userServiceImpl.changePassword(userId, passwordToChangeDto.getOldPassword(), passwordToChangeDto.getNewPassword());
    }

    @PatchMapping("/{userId}/roles")
    public User patchRoles(@PathVariable long userId, @RequestParam Set<String> roleNames) {
        return userServiceImpl.changeRoles(userId, roleNames);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userServiceImpl.deleteUser(userId);
    }

    @PatchMapping("/{userId}/team")
    public User patchTeam(@PathVariable long userId, @RequestParam String teamId) {
        return userServiceImpl.changeTeam(userId, teamId);
    }

    @PatchMapping("/{userId}/specs")
    public User patchSpecs(@PathVariable long userId, @RequestParam @Size(min = 1, max = 16, message = "Specs must be between 1 and 16.") Set<String> specsIds) {
        return userServiceImpl.changeSpecs(userId, specsIds);
    }
}
