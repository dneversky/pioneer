package dev.dneversky.pioneer.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PioneerTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerTeamApplication.class, args);
    }

}
