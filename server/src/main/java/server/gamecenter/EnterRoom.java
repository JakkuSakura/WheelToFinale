package server.gamecenter;

import io.netty.channel.Channel;
import server.GameServer;
import server.network.NetworkStringEvent;
import server.user.User;

public class EnterRoom extends NetworkHandler<NetworkStringEvent> {
    private GameServer gameServer;

    public EnterRoom(GameServer gameServer) {
        super("EnterRoom");
        this.gameServer = gameServer;
    }
    @Override
    public void handler(NetworkStringEvent network) {
        Channel channel = network.getChannel();
        String s = network.getData();
        String[] spt = s.split(" ");
        User u = gameServer.getUserManager().getPlayer("Addr", channel.remoteAddress());
        if (u == null)
            return;
        //u.enterRoom(gameServer.getGameCenter().getRoom(Integer.parseInt(spt[1])));
    }

}
