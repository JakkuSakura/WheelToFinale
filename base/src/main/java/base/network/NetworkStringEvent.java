package base.network;

import io.netty.channel.Channel;

public class NetworkStringEvent extends NetworkEvent {

    public NetworkStringEvent(Channel channel) {
        super(channel);
    }

    private String data;

    public void setData(String data) {
        this.data = data;

    }

    public String getData() {
        return data;
    }

}
