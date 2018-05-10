package server.network;

import shared.events.EventType;

import java.util.HashMap;
import java.util.Map;

public class NetWorkType extends EventType {

    public static final NetWorkType HANDLER_ADDED = new NetWorkType("HANDLER_ADDED");
    public static final NetWorkType HANDLER_REMOVED  = new NetWorkType("HANDLER_REMOVED ");
    public static final NetWorkType CHANNEL_READ_0 = new NetWorkType("CHANNEL_READ_0");
    public static final NetWorkType CHANNEL_ACTIVE = new NetWorkType("CHANNEL_ACTIVE");
    public static final NetWorkType CHANNEL_INACTIVE = new NetWorkType("CHANNEL_INACTIVE");
    public static final NetWorkType EXCEPTION_CAUGHT  = new NetWorkType("EXCEPTION_CAUGHT ");

    public static final Map<String, NetWorkType> EVENT_MAP = new HashMap<>();
    protected NetWorkType(String name) {
        super(name);
        EVENT_MAP.put(name, this);
    }
}
