package main.java.server.eventhandler;

import io.netty.channel.Channel;
import main.java.server.GameServer;
import main.java.server.network.NetworkEvent;
import main.java.server.user.User;

public class Signup extends NetworkHandler {
    private GameServer gameServer;

    public Signup(GameServer gameServer) {
        super("Signup");
        this.gameServer = gameServer;
    }

    @Override
    public void handler(NetworkEvent networkEvent) {
        Channel incoming = networkEvent.getChannel();
        User u = gameServer.getUserManager().signup(networkEvent.getData());
        if (u == null) {
            gameServer.getNetwork().sendMessage(incoming, "InputError");
            return;
        }
        gameServer.getNetwork().sendMessage(incoming, "Ok");
    }

}
