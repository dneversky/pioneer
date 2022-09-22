package dev.dneversky.pioneer.team.api;

import dev.dneversky.pioneer.team.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserWebClient {

    public Mono<User> getUserById(String id) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8000/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
