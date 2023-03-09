package ru.etysoft.dira.updates;

public class Update {

    private long originRequestId = -1;
    private long updateId = 0;
    private long updateExpireSec = 8 * 60 * 60;
    private long updateCreatedTime;
    private String roomSecret;

    public Update(long updateId) {
        this.updateId = updateId;
        updateCreatedTime = System.currentTimeMillis();
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }

    public void setUpdateExpireSec(long updateExpireSec) {
        this.updateExpireSec = updateExpireSec;
    }

    public long getUpdateExpireSec() {
        return updateExpireSec;
    }

    public boolean isExpired()
    {
        return System.currentTimeMillis() - updateCreatedTime > updateExpireSec * 1000;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public long getUpdateId() {
        return updateId;
    }

    public void setOriginRequestId(long originRequestId) {
        this.originRequestId = originRequestId;
    }
}
