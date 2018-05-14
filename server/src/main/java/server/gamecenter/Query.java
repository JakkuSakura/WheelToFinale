package server.gamecenter;

import server.GameServer;
import server.network.CommonQueryObject;
import shared.events.Event;
import shared.reactor.Chain;
import shared.reactor.SimpleEventHandler;

public class Query extends SimpleEventHandler<CommonQueryObject> {
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
    public void handler_0(Chain chain, CommonQueryObject event) {

    }

}
