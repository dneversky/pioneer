package dev.dneversky.pioneer.team.api;

import dev.dneversky.pioneer.team.entity.User;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class UserWebClient {

    public Flux<User> getUsersById(Flux<String> usersId) {
        return WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8000/users/ids")
                .body(BodyInserters.fromPublisher(usersId, String.class))
                .retrieve().bodyToFlux(User.class);
    }
}
