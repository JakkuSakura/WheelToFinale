package shared.network;

import io.netty.channel.Channel;

public class ReceiveObjectEvent extends NetworkEvent {
    private final Object object;

    public ReceiveObjectEvent(Object object, Channel channel) {
        super(channel);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
