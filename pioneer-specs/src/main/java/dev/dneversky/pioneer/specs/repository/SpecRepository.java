package dev.dneversky.pioneer.specs.repository;

import dev.dneversky.pioneer.specs.entity.Spec;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecRepository extends MongoRepository<Spec, String> {
}
