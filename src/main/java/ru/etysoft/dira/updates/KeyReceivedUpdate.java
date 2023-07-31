package ru.etysoft.dira.updates;

import ru.etysoft.dira.requests.entities.DhKey;

public class KeyReceivedUpdate extends Update {
    private DhKey dhKey;

    public KeyReceivedUpdate(DhKey dhKey) {
        super(0, UpdateType.KEY_RECEIVED_UPDATE);
        this.dhKey = dhKey;
        setUpdateExpireSec(10);
    }

    public DhKey getDhKey() {
        return dhKey;
    }

    public void setDhKey(DhKey dhKey) {
        this.dhKey = dhKey;
    }
}
