package server.action;

import io.netty.channel.Channel;
import server.game.GameLogic;
import server.user.User;

public class EnterRoom implements GameAction {
    private GameLogic gameLogic;

    public EnterRoom(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void run(Channel incoming, String s) {
        String[] spt = s.split(" ");
        User u = gameLogic.getUsers().getPlayer("Addr", incoming.remoteAddress());
        if (u == null)
            return;
        u.enterRoom(gameLogic.getRooms().getRoom(spt[1]));
    }

    @Override
    public String getMethodName() {
        return "enterroom";
    }
}
