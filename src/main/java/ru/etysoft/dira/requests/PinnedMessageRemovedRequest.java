package ru.etysoft.dira.requests;

public class PinnedMessageRemovedRequest extends Request {

    private String messageId;

    private String roomSecret;

    private String userId;

    public PinnedMessageRemovedRequest(String roomSecret, String messageId, String userId) {
        super(0, RequestType.PINNED_MESSAGE_REMOVED_REQUEST);
        this.messageId = messageId;
        this.userId = userId;
        this.roomSecret = roomSecret;
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

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
