package server.gamecenter;

import server.network.NetworkEvent;
import shared.events.Event;
import shared.events.Priority;
import shared.events.SimpleEventHandler;

public abstract class NetworkHandler<T extends NetworkEvent> extends SimpleEventHandler {
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

    @SuppressWarnings("unchecked")
    @Override
    public void handler(Event event) {
        try {
            T converted = (T) event;
            handler(converted);
        } catch (ClassCastException e) {
            throw new RuntimeException("Event cannot be converted" + event.toString());

        }
    }

    public abstract void handler(T networkEvent);
}
