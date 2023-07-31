package ru.etysoft.dira.requests.encryption;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RequestType;
import ru.etysoft.dira.requests.entities.BaseMember;

public class SubmitKeyRequest extends Request {

    private String roomSecret;
    private BaseMember baseMember;

    public SubmitKeyRequest(String roomSecret, BaseMember baseMember) {
        super(0, RequestType.SUBMIT_KEY);
        this.roomSecret = roomSecret;
        this.baseMember = baseMember;
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }

    public BaseMember getBaseMember() {
        return baseMember;
    }

    public void setBaseMember(BaseMember baseMember) {
        this.baseMember = baseMember;
    }
}
