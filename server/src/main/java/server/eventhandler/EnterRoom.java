package main.java.server.eventhandler;

import io.netty.channel.Channel;
import main.java.server.GameServer;
import main.java.server.network.NetworkEvent;
import main.java.server.user.User;

public class EnterRoom extends NetworkHandler {
    private GameServer gameServer;

    public EnterRoom(GameServer gameServer) {
        super("EnterRoom");
        this.gameServer = gameServer;
    }
    @Override
    public void handler(NetworkEvent network) {
        Channel channel = network.getChannel();
        String s = network.getData();
        String[] spt = s.split(" ");
        User u = gameServer.getUserManager().getPlayer("Addr", channel.remoteAddress());
        if (u == null)
            return;
        u.enterRoom(gameServer.getGameCenter().getRoom(Integer.parseInt(spt[1])));
    }

}
