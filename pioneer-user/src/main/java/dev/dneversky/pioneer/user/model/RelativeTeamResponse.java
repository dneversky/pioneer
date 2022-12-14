package dev.dneversky.pioneer.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RelativeTeamResponse {

    private int statusCode;
    private List<String> changedUsers;
    private List<String> unchangedUsers;

    public RelativeTeamResponse(List<String> changedUsers, List<String> unchangedUsers) {
        this.changedUsers = changedUsers;
        this.unchangedUsers = unchangedUsers;
        this.calculateStatusCode();
    }

    private void calculateStatusCode() {
        if(changedUsers.size() >= 1 && unchangedUsers.size() == 0) {
            setStatusCode(1);
        } else if (changedUsers.size() >= 1) {
            setStatusCode(0);
        } else if(unchangedUsers.size() >= 1) {
            setStatusCode(-1);
        }
    }
}
