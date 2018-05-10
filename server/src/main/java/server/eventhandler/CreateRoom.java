package main.java.server.eventhandler;

import main.java.server.GameServer;
import main.java.server.network.NetworkEvent;

public class CreateRoom extends NetworkHandler {
    private GameServer gameServer;

    public CreateRoom(GameServer gameServer) {
        super("CreateRoom");
        this.gameServer = gameServer;
    }

    @Override
    public void handler(NetworkEvent networkEvent) {
        String[] spt = networkEvent.getData().split(" ");
        if (gameServer.getUserManager().getPlayer("Addr", networkEvent.getChannel().remoteAddress()) != null)
            gameServer.getGameCenter().createRoom(spt[1]);

    }
}
