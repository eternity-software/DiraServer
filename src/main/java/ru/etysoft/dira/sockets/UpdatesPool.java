package ru.etysoft.dira.sockets;

import ru.etysoft.dira.updates.Update;

import java.util.ArrayList;
import java.util.List;

public class UpdatesPool {

    private List<Update> updates = new ArrayList<>();
    private List<ClientHandlerContract> connectedClients = new ArrayList<>();

    private MasterSocketContract masterSocketContract;
    private long lastUpdateId = 0;

    public UpdatesPool(MasterSocketContract masterSocketContract) {
        this.masterSocketContract = masterSocketContract;
    }

    public List<ClientHandlerContract> getConnectedClients() {
        for(ClientHandlerContract clientHandlerContract : new ArrayList<>(connectedClients))
        {
            if(!masterSocketContract.hasClient(clientHandlerContract.getAddress()))
            {
                connectedClients.remove(clientHandlerContract);
            }
        }
        return new ArrayList<>(connectedClients);
    }

    public List<Update> getUpdates() {
        for(Update update : new ArrayList<>(updates))
        {
            if(update.isExpired())
            {
                updates.remove(update);
            }
        }
        return new ArrayList<>(updates);
    }

    public void registerClient(ClientHandlerContract clientHandlerContract)
    {
        if(!connectedClients.contains(clientHandlerContract))
        {
            connectedClients.add(clientHandlerContract);
        }
    }

    public void unregisterClient(ClientHandlerContract clientHandlerContract)
    {
        connectedClients.remove(clientHandlerContract);
    }

    public List<Update> getUpdatesAfter(long updateId) {
        List<Update> filteredUpdates = new ArrayList<>(getUpdates());
        for(Update update : new ArrayList<>(filteredUpdates))
        {
            if(update.getUpdateId() <= updateId)
            {
                filteredUpdates.remove(update);
            }
        }
        return filteredUpdates;
    }

    public void registerUpdate(Update update)
    {
        update.setUpdateId(lastUpdateId);
        updates.add(update);
        lastUpdateId++;
    }
}
