package dev.dneversky.pioneer.team;

public class MessageModel {

    private Long userId;
    private String teamId;

    public MessageModel() {}

    public MessageModel(Long userId, String teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
