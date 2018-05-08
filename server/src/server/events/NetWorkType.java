package server.events;

public class NetWorkType extends EventType {
    public static final NetWorkType HANDLER_ADDED = new NetWorkType(1);
    public static final NetWorkType HANDLER_REMOVED  = new NetWorkType(2);
    public static final NetWorkType CHANNEL_READ_0 = new NetWorkType(3);
    public static final NetWorkType CHANNEL_ACTIVE = new NetWorkType(4);
    public static final NetWorkType CHANNEL_INACTIVE = new NetWorkType(5);
    public static final NetWorkType EXCEPTION_CAUGHT  = new NetWorkType(6);

    protected NetWorkType(int v) {
        super(v);
    }
}
