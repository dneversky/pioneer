package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String nickname;
    private Team team;
    private Set<Spec> specs = new HashSet<>();
}
