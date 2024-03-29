package ru.etysoft.dira.requests;

import java.util.ArrayList;
import java.util.List;

public class SubscribeRequest extends Request {

    private List<String> roomSecrets = new ArrayList<>();

    public SubscribeRequest(long requestId) {
        super(requestId, RequestType.SUBSCRIBE_REQUEST);
    }

    public List<String> getRoomSecrets() {
        return roomSecrets;
    }

    public void setRoomSecrets(List<String> roomSecrets) {
        this.roomSecrets = roomSecrets;
    }
}
