package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeamToCreateDto {

    private long memberId;
    private Set<String> specsIds = new HashSet<>();
}
