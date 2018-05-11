package server.eventhandler;

import io.netty.channel.Channel;
import server.user.User;
import server.GameServer;
import server.network.NetworkEvent;

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
        //u.enterRoom(gameServer.getGameCenter().getRoom(Integer.parseInt(spt[1])));
    }

}
