package dev.dneversky.pioneer.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PioneerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerProjectApplication.class, args);
    }

}
