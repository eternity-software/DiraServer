package ru.etysoft.dira.requests.entities;

public class Status {
    private final UserStatus userStatus;

    private final String userId;

    private final String secretName;

    private long time;

    public Status(UserStatus userStatus, String userId, String roomSecret) {
        this.userStatus = userStatus;
        this.userId = userId;
        this.secretName = roomSecret;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getUserId() {
        return userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSecretName() {
        return secretName;
    }
}
