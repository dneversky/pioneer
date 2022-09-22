package dev.dneversky.pioneer.team.repository;

import dev.dneversky.pioneer.team.entity.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
}
