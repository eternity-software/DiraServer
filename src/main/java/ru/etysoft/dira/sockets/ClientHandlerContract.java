package ru.etysoft.dira.sockets;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.updates.Update;

public interface ClientHandlerContract {

    void onRequest(Request request);

    void sendUpdate(Update update);

    String getAddress();
    boolean isOpen();

}
