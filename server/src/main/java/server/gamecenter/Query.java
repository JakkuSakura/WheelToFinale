package server.gamecenter;

import server.GameServer;
import shared.events.Event;
import shared.reactor.Chain;
import shared.reactor.EventHandler;

public class Query extends EventHandler {
    private GameServer gameServer;

    public Query(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    private GameCenterInfo getGameCenterInfo() {
        return new GameCenterInfo();
    }

    private ServerInfo getServerInfo() {
        return new ServerInfo();
    }

    @Override
    public boolean check(Event event) {
//        return ;
//        todo
        return true;
    }

    @Override
    public void handler(Chain chain, Event event) {

    }

}
