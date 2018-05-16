package shared.network;

import shared.reactor.Priority;
import shared.reactor.EventHandler;

public abstract class NetworkHandler<T extends NetworkEvent> extends EventHandler {
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
