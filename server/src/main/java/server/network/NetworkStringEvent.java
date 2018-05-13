package server.network;

import io.netty.channel.Channel;

public class NetworkStringEvent extends NetworkEvent {


    public NetworkStringEvent(NetworkType type, Channel channel) {
        super(type, channel);
    }

    private String data;

    public void setData(String data) {
        this.data = data;

    }

    public String getData() {
        return data;
    }

}
