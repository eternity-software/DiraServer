package ru.etysoft.dira.requests;

public class VerifyRoomInfoRequest extends Request {

    private String name, roomSecret;

    public VerifyRoomInfoRequest(long requestId) {
        super(requestId, RequestType.VERIFY_ROOM_INFO);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }
}
