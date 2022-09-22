package dev.dneversky.pioneer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class PioneerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route
                        .path("/users/**")
                        .uri("lb://user-service"))
                .route(route -> route
                        .path("/teams/**")
                        .uri("lb://team-service"))
                .build();
    }
}
