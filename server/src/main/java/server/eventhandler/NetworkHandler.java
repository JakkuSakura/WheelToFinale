package main.java.server.eventhandler;

import main.java.server.network.NetworkEvent;
import main.java.shared.events.Event;
import main.java.shared.events.Priority;
import main.java.shared.events.SimpleEventHandler;

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
