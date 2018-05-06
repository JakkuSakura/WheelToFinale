package server.action;

import io.netty.channel.Channel;
import server.game.GameLogic;

public class CreateRoom implements GameAction {
    private GameLogic gameLogic;

    public CreateRoom(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void run(Channel incoming, String s) {
        String[] spt = s.split(" ");
        if (gameLogic.getUsers().getPlayer("Addr", incoming.remoteAddress()) != null)
            gameLogic.getRooms().createRoom(spt[1]);

    }

    @Override
    public String getMethodName() {
        return "createroom";
    }
}
