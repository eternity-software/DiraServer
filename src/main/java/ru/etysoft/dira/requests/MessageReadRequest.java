package ru.etysoft.dira.requests;

public class MessageReadRequest extends Request {

    private String userId;

    private long readTime;

    private String messageId;

    private final String roomSecret;

    public MessageReadRequest(String userId, long readTime, String messageId, String roomSecret) {
        super(0, RequestType.MESSAGE_READ_REQUEST);
        this.userId = userId;
        this.readTime = readTime;
        this.messageId = messageId;
        this.roomSecret = roomSecret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getReadTime() {
        return readTime;
    }

    public void setReadTime(long readTime) {
        this.readTime = readTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRoomSecret() {
        return roomSecret;
    }
}
