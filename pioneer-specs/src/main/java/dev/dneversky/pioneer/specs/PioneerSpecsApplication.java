package dev.dneversky.pioneer.specs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PioneerSpecsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PioneerSpecsApplication.class, args);
	}

}
