package ru.etysoft.dira.sockets;

public interface MasterSocketContract {

    void registerClient(ClientHandler clientHandler);

    void unregisterClient(ClientHandlerContract clientHandler);

    boolean hasClient(String address);

    RoomUpdatesPool getUpdatesPool(String roomSecret);

}
