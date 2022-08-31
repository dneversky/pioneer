package dev.dneversky.pioneer.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "dev.dneversky.pioneer.project.repository")
public class MongoConfig { }
