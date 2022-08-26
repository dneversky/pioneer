package dev.dneversky.pioneer.team.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "dev.dneversky.pioneer.team.repository")
public class MongoConfig { }
