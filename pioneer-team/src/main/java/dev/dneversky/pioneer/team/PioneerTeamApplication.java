package dev.dneversky.pioneer.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableReactiveMongoRepositories
public class PioneerTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerTeamApplication.class, args);
    }

}
