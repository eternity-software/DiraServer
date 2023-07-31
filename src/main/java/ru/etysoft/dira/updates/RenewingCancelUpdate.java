package ru.etysoft.dira.updates;

public class RenewingCancelUpdate extends Update{

    public RenewingCancelUpdate() {
        super(0, UpdateType.RENEWING_CANCEL);
        setUpdateExpireSec(10);
    }

}