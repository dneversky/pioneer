package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateTeamDto {
    private String id;
    private List<Long> membersIds;
    private List<String> specsIds;
}
