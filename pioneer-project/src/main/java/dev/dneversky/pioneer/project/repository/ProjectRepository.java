package dev.dneversky.pioneer.project.repository;

import dev.dneversky.pioneer.project.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
