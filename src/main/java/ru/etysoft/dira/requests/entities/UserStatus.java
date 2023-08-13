package ru.etysoft.dira.requests.entities;

public class UserStatus {
    private final UserStatusType userStatusType;

    private final String userId;

    private final String secretName;

    private long time;

    public UserStatus(UserStatusType userStatusType, String userId, String roomSecret) {
        this.userStatusType = userStatusType;
        this.userId = userId;
        this.secretName = roomSecret;
    }

    public UserStatusType getUserStatus() {
        return userStatusType;
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
