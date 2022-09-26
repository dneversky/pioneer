package dev.dneversky.pioneer.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelativeTeamRequest {

    private List<String> usersId;
    private String teamId;
}
