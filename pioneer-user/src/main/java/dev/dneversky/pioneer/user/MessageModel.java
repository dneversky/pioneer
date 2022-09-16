package dev.dneversky.pioneer.user;

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

    @Override
    public String toString() {
        return "MessageModel{" +
                "userId=" + userId +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
