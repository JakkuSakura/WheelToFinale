package server.network;
import io.netty.channel.Channel;
import server.eventhandler.NetworkHandler;

public class ReadFromXML extends NetworkHandler {

    @Override
    public void handler(NetworkEvent networkEvent) {
        Channel channel = networkEvent.getChannel();
        String data = networkEvent.getData();


    }

}
