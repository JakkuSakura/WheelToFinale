package shared.network;

import io.netty.channel.Channel;


public class SendObjectEvent extends NetworkEvent {

    public SendObjectEvent(Channel channel) {
        super(channel);
    }
    private Object object;

    public SendObjectEvent() {
        this(null);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
