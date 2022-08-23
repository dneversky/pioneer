package dev.dneversky.pioneer.user.service.impl;

import dev.dneversky.pioneer.user.entity.Role;
import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.entity.UserDetails;
import dev.dneversky.pioneer.user.exception.UnequalPasswords;
import dev.dneversky.pioneer.user.exception.UserWithIdNotFoundException;
import dev.dneversky.pioneer.user.exception.UserWithUsernameExistsException;
import dev.dneversky.pioneer.user.repository.UserRepository;
import dev.dneversky.pioneer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserWithIdNotFoundException(id));
    }

    @Override
    public User saveUser(User user) {
        userRepository.findByDetails(user.getDetails()).orElseThrow(
                () -> new UserWithUsernameExistsException(user.getDetails().getUsername()));
        user.setDetails(createUserDetails(user.getDetails()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new UserWithIdNotFoundException(user.getId()));
        return userRepository.save(user);
    }

    @Override
    public User patchRole(long userId, Collection<String> roleNames) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        user.getDetails().setRoles(roleNames.stream().map(Role::valueOf).collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    @Override
    public User patchPassword(long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        String encodedOldPassword = passwordEncoder.encode(oldPassword);
        if(!user.getDetails().getPassword().equals(encodedOldPassword)) {
            throw new UnequalPasswords("Old and current passwords are not equal.");
        }
        user.getDetails().setPassword(encodedOldPassword);
        return userRepository.save(user);
    }

    private UserDetails createUserDetails(UserDetails userDetails) {
        String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(encodedPassword);
        userDetails.setRoles(Collections.singleton(Role.USER));
        userDetails.setEnabled(true);

        return userDetails;
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserWithIdNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public User setTeam(long userId, String teamId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        user.setTeamId(teamId);

        return userRepository.save(user);
    }

    @Override
    public User removeTeam(long userId, String teamId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserWithIdNotFoundException(userId));
        user.setTeamId(null);

        return userRepository.save(user);
    }

    @Override
    public User addSpecs(long userId, Set<String> specs) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        user.getSpecs().addAll(specs);

        return userRepository.save(user);
    }

    @Override
    public User removeSpecs(long userId, Set<String> specs) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        user.getSpecs().removeAll(specs);

        return userRepository.save(user);
    }
}
