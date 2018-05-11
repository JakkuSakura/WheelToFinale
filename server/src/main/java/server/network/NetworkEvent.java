package server.network;

import io.netty.channel.Channel;
import shared.events.Event;

public class NetworkEvent extends Event {
    private Channel channel;
    private String data;
    public NetworkEvent(server.network.NetworkType type, Channel channel) {
        super(type, channel.toString());
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
