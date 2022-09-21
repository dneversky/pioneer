package dev.dneversky.pioneer.user.repository;

import dev.dneversky.pioneer.user.entity.UserDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserDetailsRepository extends ReactiveMongoRepository<UserDetails, String> {
}
