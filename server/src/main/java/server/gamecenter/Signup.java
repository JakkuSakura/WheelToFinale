package server.gamecenter;

import io.netty.channel.Channel;
import server.GameServer;
import server.network.NetworkStringEvent;
import server.user.User;

public class Signup extends NetworkHandler<NetworkStringEvent> {
    private GameServer gameServer;

    public Signup(GameServer gameServer) {
        super("Signup");
        this.gameServer = gameServer;
    }

    @Override
    public void handler(NetworkStringEvent networkEvent) {
        Channel incoming = networkEvent.getChannel();
        User u = gameServer.getUserManager().signup(networkEvent.getData());
        if (u == null) {
            incoming.writeAndFlush("InputError" +"\r\n");
            return;
        }
        incoming.writeAndFlush("Ok" +"\r\n");
    }

}
