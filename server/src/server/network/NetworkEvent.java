package server.network;

import io.netty.channel.Channel;

public class NetworkEvent extends shared.events.Event {
    private Channel channel;
    private String data;
    public NetworkEvent(NetWorkType type, Channel channel, String name) {
        super(type, name);
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
