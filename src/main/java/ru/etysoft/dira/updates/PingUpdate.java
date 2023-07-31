package ru.etysoft.dira.updates;

/**
 * Ask all room members to send their BaseMember status
 */
public class PingUpdate extends Update{
    public PingUpdate() {
        super(0, UpdateType.PING_UPDATE);
        setUpdateExpireSec(100);
    }
}
