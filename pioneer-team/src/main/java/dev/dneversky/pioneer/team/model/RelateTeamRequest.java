package dev.dneversky.pioneer.team.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelateTeamRequest {

    private List<String> usersId;
    private String teamId;
}
