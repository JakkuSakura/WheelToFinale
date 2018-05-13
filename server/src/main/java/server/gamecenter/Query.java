package server.gamecenter;

import server.GameServer;
import server.network.PreSendObjectEvent;

public class Query {
    private GameServer gameServer;

    public Query(GameServer gameServer) {
        this.gameServer = gameServer;
    }


    void parser(Object object) {
        if (object instanceof QueryCommand) {
            QueryCommand queryCommand = (QueryCommand) object;
            //TODO check if user valid
            switch (queryCommand.getOperation()) {
                case "Update":

                    break;
                case "Query":
                    switch (queryCommand.getRoomNumber()) {
                        case -1: {
                            ServerInfo serverInfo = getServerInfo();
                            PreSendObjectEvent preSendObjectEvent = new PreSendObjectEvent();
                            preSendObjectEvent.setObject(serverInfo);
                            break;
                        }
                        case -2: {
                            GameCenterInfo gameCenterInfo = getGameCenterInfo();
                            PreSendObjectEvent preSendObjectEvent = new PreSendObjectEvent();
                            preSendObjectEvent.setObject(gameCenterInfo);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private GameCenterInfo getGameCenterInfo() {
        return new GameCenterInfo();
    }

    private ServerInfo getServerInfo() {
        return new ServerInfo();
    }
}
