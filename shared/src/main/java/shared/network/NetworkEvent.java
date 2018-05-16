package shared.network;

import io.netty.channel.Channel;
import shared.events.Event;

public class NetworkEvent extends Event {
    private Channel channel;
    public NetworkEvent(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
