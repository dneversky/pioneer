package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class Team {
    private String id;
    private Set<User> members = new HashSet<>();
    private Set<Spec> specs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", members=" + members +
                ", specs=" + specs +
                '}';
    }
}
