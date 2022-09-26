package dev.dneversky.pioneer.team.api;

import dev.dneversky.pioneer.team.model.RelateTeamRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserWebClient {

    public Flux<String> getUsersById(Mono<RelateTeamRequest> requestMono) {
        return WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8000/users/ids")
                .body(BodyInserters.fromPublisher(requestMono, RelateTeamRequest.class))
                .retrieve().bodyToFlux(String.class);
    }
}
