package main.java.server.network;

import main.java.shared.events.EventType;

import java.util.HashMap;
import java.util.Map;

public class NetworkType extends EventType {

    public static final NetworkType HANDLER_ADDED = new NetworkType("HANDLER_ADDED");
    public static final NetworkType HANDLER_REMOVED  = new NetworkType("HANDLER_REMOVED ");
    public static final NetworkType CHANNEL_READ_0 = new NetworkType("CHANNEL_READ_0");
    public static final NetworkType CHANNEL_ACTIVE = new NetworkType("CHANNEL_ACTIVE");
    public static final NetworkType CHANNEL_INACTIVE = new NetworkType("CHANNEL_INACTIVE");
    public static final NetworkType EXCEPTION_CAUGHT  = new NetworkType("EXCEPTION_CAUGHT ");
    public static final NetworkType SEND_DATA = new NetworkType("SEND_DATA");
    public static final NetworkType MESSAGE_RECEIVED = CHANNEL_READ_0;
    public static final Map<String, NetworkType> EVENT_MAP = new HashMap<>();

    protected NetworkType(String name) {
        super(name);
        EVENT_MAP.put(name, this);
    }
}
