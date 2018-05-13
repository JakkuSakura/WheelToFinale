package server.network;
import io.netty.channel.Channel;
import server.gamecenter.NetworkHandler;
import server.gamecenter.Query;
import shared.XmlUtil;

public class ReadFromXML extends NetworkHandler<NetworkStringEvent> {
    private Query query;

    public ReadFromXML(Query query) {
        this.query = query;
    }
    @Override
    public void handler(NetworkStringEvent networkEvent) {
        Channel channel = networkEvent.getChannel();
        String data = networkEvent.getData();
        Object object = XmlUtil.fromXML(data);
        query.parser(object);
    }
}
