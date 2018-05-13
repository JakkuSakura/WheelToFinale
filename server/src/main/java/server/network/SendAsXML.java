package server.network;

import io.netty.channel.Channel;
import server.gamecenter.NetworkHandler;
import shared.XmlUtil;


public class SendAsXML extends NetworkHandler<PreSendObjectEvent> {
    @Override
    public void handler(PreSendObjectEvent preSendObjectEvent) {
        Channel channel = preSendObjectEvent.getChannel();
        String data = XmlUtil.toXML(preSendObjectEvent.getObject());
        channel.writeAndFlush(data + "\r\n");
    }
}
