package server.action;

import io.netty.channel.Channel;
import server.game.GameLogic;
import server.user.User;

public class UserAction implements GameAction {

    private GameLogic gameLogic;

    public UserAction(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void run(Channel incoming, String s) {
        User u = gameLogic.getUsers().getPlayer("Addr", incoming.remoteAddress());
        if (u == null)
            return;
        gameLogic.receiveMessage(u, s);
    }

    @Override
    public String getMethodName() {
        return null;
    }
}
