package top.zhuoxinsocial.server.game;

import top.zhuoxinsocial.server.network.GameServer;
import top.zhuoxinsocial.server.network.Network;
import top.zhuoxinsocial.server.room.Rooms;
import top.zhuoxinsocial.server.user.Users;

public class Game {
    private Users users = new Users();
    private Rooms rooms = new Rooms();
    private GameLogic gameLogic = new GameLogic(users);
    private Network network = new GameServer(8080, gameLogic);

    public void run() throws Exception {
        network.run();
    }
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.run();
    }


}
