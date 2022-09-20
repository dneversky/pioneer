package dev.dneversky.pioneer.user.repository;

import dev.dneversky.pioneer.user.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Optional<User> findByDetailsUsername(String username);
}
