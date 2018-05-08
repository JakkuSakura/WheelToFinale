package server;

import io.netty.channel.Channel;
import server.game.GameLogic;
import server.network.GameServerNetwork;
import server.room.Rooms;
import server.user.Users;

public class GameServer {
    private Users users = new Users();
    private Rooms rooms = new Rooms();
    private GameLogic gameLogic = new GameLogic(users);
    private GameServerNetwork network = new GameServerNetwork(8080, this);

    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.run();
    }

    public void processMessage(Channel incoming, String s) {
        gameLogic.processMessage(incoming, s);
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void run() throws Exception {
        network.run();
    }


}
