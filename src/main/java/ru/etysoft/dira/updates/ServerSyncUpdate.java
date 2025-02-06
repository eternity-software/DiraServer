package ru.etysoft.dira.updates;

import ru.etysoft.dira.DiraServer;
import ru.etysoft.dira.sockets.MasterSocket;

import java.util.List;

public class ServerSyncUpdate extends Update {

    private List<String> supportedApis = MasterSocket.SUPPORTED_APIS;
    private long timeServerStart = DiraServer.timeSeverStartup;

    private String fileServerUrl = "https://files.diranetwork.com";

    public ServerSyncUpdate() {
        super(0, UpdateType.SERVER_SYNC);
    }

    public String getFileServerUrl() {
        return fileServerUrl;
    }

    public void setFileServerUrl(String fileServerUrl) {
        this.fileServerUrl = fileServerUrl;
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

    public void setSupportedApis(List<String> supportedApis) {
        this.supportedApis = supportedApis;
    }
}
