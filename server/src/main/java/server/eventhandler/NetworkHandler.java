package server.eventhandler;

import server.network.NetworkEvent;
import shared.events.Event;
import shared.events.Priority;
import shared.events.SimpleEventHandler;

public abstract class NetworkHandler extends SimpleEventHandler {
    private final String name;

    public NetworkHandler() {
        this("NetworkHandler");
    }

    public NetworkHandler(String name) {
        super();
        setLevel(Priority.HIGH.getLevel());
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public void handler(Event event) {
        if (event instanceof NetworkEvent) {
            NetworkEvent converted = (NetworkEvent) event;
            handler(converted);
        }
        else
            throw new RuntimeException("Event should be NetworkEvent:" + event.toString());
    }

    public abstract void handler(NetworkEvent networkEvent);
}
