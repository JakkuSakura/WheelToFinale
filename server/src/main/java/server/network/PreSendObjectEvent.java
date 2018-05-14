package server.network;

import io.netty.channel.Channel;


public class PreSendObjectEvent extends NetworkEvent {
    public PreSendObjectEvent(Channel channel) {
        super(channel);
    }
    private Object object;

    public PreSendObjectEvent() {
        this(null);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
