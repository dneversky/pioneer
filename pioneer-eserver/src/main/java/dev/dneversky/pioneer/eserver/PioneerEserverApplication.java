package dev.dneversky.pioneer.eserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PioneerEserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerEserverApplication.class, args);
    }

}
