package main.java.server.network;

import io.netty.channel.Channel;
import main.java.server.eventhandler.NetworkHandler;

public class ReadFromXML extends NetworkHandler {

    @Override
    public void handler(NetworkEvent networkEvent) {
        Channel channel = networkEvent.getChannel();
        String data = networkEvent.getData();


    }

}
