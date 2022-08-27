package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeamToCreateDto {

    private Set<Long> membersIds = new HashSet<>();
    private Set<String> specsIds = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamToCreateDto teamToCreateDto = (TeamToCreateDto) o;
        return Objects.equals(membersIds, teamToCreateDto.membersIds) && Objects.equals(specsIds, teamToCreateDto.specsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membersIds, specsIds);
    }
}
