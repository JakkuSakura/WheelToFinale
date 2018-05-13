package server.gamecenter;

import server.GameServer;
import server.network.NetworkStringEvent;

public class CreateRoom extends NetworkHandler<NetworkStringEvent> {
    private GameServer gameServer;

    public CreateRoom(GameServer gameServer) {
        super("CreateRoom");
        this.gameServer = gameServer;
    }

    @Override
    public void handler(NetworkStringEvent networkEvent) {
        String[] spt = networkEvent.getData().split(" ");
        if (gameServer.getUserManager().getPlayer("Addr", networkEvent.getChannel().remoteAddress()) != null)
            gameServer.getGameCenter().createRoom(spt[1]);

    }
}
