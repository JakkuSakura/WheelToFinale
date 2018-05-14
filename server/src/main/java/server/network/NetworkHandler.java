package server.network;

import shared.reactor.Priority;
import shared.reactor.SimpleEventHandler;

public abstract class NetworkHandler<T extends NetworkEvent> extends SimpleEventHandler<T> {
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


}
