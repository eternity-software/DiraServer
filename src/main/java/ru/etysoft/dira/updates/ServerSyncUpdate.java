package ru.etysoft.dira.updates;

import ru.etysoft.dira.sockets.MasterSocket;

import java.util.List;

public class ServerSyncUpdate extends Update {

    private List<String> supportedApis = MasterSocket.SUPPORTED_APIS;

    public ServerSyncUpdate(long updateId) {
        super(updateId);
    }

    public List<String> getSupportedApis() {
        return supportedApis;
    }
}
