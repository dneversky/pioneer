package dev.dneversky.pioneer.team.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelateTeamResponse {

    private int statusCode;
    private List<String> changedUsers;
    private List<String> unchangedUsers;
}
