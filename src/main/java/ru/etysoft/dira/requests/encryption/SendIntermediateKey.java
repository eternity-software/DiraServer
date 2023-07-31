package ru.etysoft.dira.requests.encryption;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RequestType;
import ru.etysoft.dira.requests.entities.DhKey;
import ru.etysoft.dira.updates.Update;
import ru.etysoft.dira.updates.UpdateType;

public class SendIntermediateKey extends Request {
    private DhKey dhKey;

    private String roomSecret;

    public SendIntermediateKey(DhKey dhKey, String roomSecret) {
        super(0, RequestType.SEND_INTERMEDIATE_KEY);
        this.dhKey = dhKey;
        this.roomSecret = roomSecret;
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }

    public DhKey getDhKey() {
        return dhKey;
    }

    public void setDhKey(DhKey dhKey) {
        this.dhKey = dhKey;
    }
}
