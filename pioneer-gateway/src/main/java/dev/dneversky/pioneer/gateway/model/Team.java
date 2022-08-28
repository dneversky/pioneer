package dev.dneversky.pioneer.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @NotBlank(message = "Id must not be null.")
    private String id;

    @Size(min = 1, max = 8, message = "Members must be between 1 and 8.")
    private List<Long> membersId = new ArrayList<>();

    @Size(min = 1, max = 8, message = "Specs must be between 1 and 16.")
    private List<String> specsId = new ArrayList<>();

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
                ", members=" + membersId +
                ", specs=" + specsId +
                '}';
    }
}
