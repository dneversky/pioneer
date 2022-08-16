package dev.dneversky.pioneer.team.repository;

import dev.dneversky.pioneer.team.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
