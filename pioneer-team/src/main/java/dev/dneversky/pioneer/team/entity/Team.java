package dev.dneversky.pioneer.team.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.*;

@Data
@Document(collection = "teams")
@NoArgsConstructor
public class Team {

    @Id
    private String id;

    @Size(min = 1, max = 8, message = "Members must be between 1 and 8.")
    private List<String> members = new ArrayList<>();

    @Size(min = 1, max = 8, message = "Specs must be between 1 and 16.")
    private Collection<String> specs = new HashSet<>();

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
