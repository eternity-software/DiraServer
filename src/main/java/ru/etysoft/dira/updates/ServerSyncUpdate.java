package ru.etysoft.dira.updates;

import ru.etysoft.dira.DiraServer;
import ru.etysoft.dira.sockets.MasterSocket;

import java.util.List;

public class ServerSyncUpdate extends Update {

    private List<String> supportedApis = MasterSocket.SUPPORTED_APIS;
    private long timeServerStart = DiraServer.timeSeverStartup;

    public ServerSyncUpdate() {
        super(0, UpdateType.SERVER_SYNC);
    }

    public void setSupportedApis(List<String> supportedApis) {
        this.supportedApis = supportedApis;
    }

    public long getTimeServerStart() {
        return timeServerStart;
    }

    public void setTimeServerStart(long timeServerStart) {
        this.timeServerStart = timeServerStart;
    }

    public List<String> getSupportedApis() {
        return supportedApis;
    }
}
