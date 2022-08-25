package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeamBody {

    private Set<Long> membersIds = new HashSet<>();
    private Set<String> specsIds = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamBody teamBody = (TeamBody) o;
        return Objects.equals(membersIds, teamBody.membersIds) && Objects.equals(specsIds, teamBody.specsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membersIds, specsIds);
    }

    @Override
    public String toString() {
        return "TeamBody{" +
                "membersIds=" + membersIds +
                ", specsIds=" + specsIds +
                '}';
    }
}
