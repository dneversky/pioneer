package dev.dneversky.pioneer.gateway.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Team {
    private String id;
    private Set<User> members = new HashSet<>();
    private Set<Spec> specs = new HashSet<>();
}
