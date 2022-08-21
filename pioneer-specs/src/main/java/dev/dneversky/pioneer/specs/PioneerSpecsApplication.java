package dev.dneversky.pioneer.specs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableMongoRepositories
public class PioneerSpecsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PioneerSpecsApplication.class, args);
	}

}
