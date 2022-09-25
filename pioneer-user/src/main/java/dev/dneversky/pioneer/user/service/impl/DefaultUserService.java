package dev.dneversky.pioneer.user.service.impl;

import dev.dneversky.pioneer.user.entity.Role;
import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.entity.UserDetails;
import dev.dneversky.pioneer.user.exception.UnequalPasswordsException;
import dev.dneversky.pioneer.user.exception.UserWithIdNotFoundException;
import dev.dneversky.pioneer.user.repository.UserRepository;
import dev.dneversky.pioneer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
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
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(Mono<String> id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> getUsersById(Flux<String> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public Mono<User> createUser(Mono<User> userMono) {
        return userRepository.saveAll(userMono).next();
    }

    @Override
    public Mono<User> updateUserById(String id, User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> changeRoles(String userId, Collection<String> roleNames) {
        User user = userRepository.findById(userId).doOnError(e -> Mono.error(new UserWithIdNotFoundException(userId))).block();
        user.getDetails().setRoles(roleNames.stream().map(Role::valueOf).collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    @Override
    public Mono<User> changePassword(String userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).doOnError(e -> Mono.error(new UserWithIdNotFoundException(userId))).block();
        String encodedOldPassword = passwordEncoder.encode(oldPassword);
        if(!user.getDetails().getPassword().equals(encodedOldPassword)) {
            throw new UnequalPasswordsException("Old and current passwords are not equal.");
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
    public Mono<Void> deleteUserById(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> changeTeam(String userId, String teamId) {
        User user = userRepository.findById(userId).doOnError(e -> Mono.error(new UserWithIdNotFoundException(userId))).block();
        user.setTeamId(teamId);

        return userRepository.save(user);
    }

    @Override
    public Mono<User> changeSpecs(String userId, Set<String> specs) {
        User user = userRepository.findById(userId).doOnError(e -> Mono.error(new UserWithIdNotFoundException(userId))).block();
        user.setSpecs(specs);

        return userRepository.save(user);
    }
}
