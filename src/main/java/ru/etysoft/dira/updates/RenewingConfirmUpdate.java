package ru.etysoft.dira.updates;

public class RenewingConfirmUpdate extends Update {

    private long timeKeyConfirmed;

    public RenewingConfirmUpdate(long timeKeyConfirmed) {
        super(0, UpdateType.RENEWING_CONFIRMED);
        this.timeKeyConfirmed = timeKeyConfirmed;
        setUpdateExpireSec(10);
    }

    public long getTimeKeyConfirmed() {
        return timeKeyConfirmed;
    }

    public void setTimeKeyConfirmed(long timeKeyConfirmed) {
        this.timeKeyConfirmed = timeKeyConfirmed;
    }
}
