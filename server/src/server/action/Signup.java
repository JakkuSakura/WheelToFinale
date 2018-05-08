package server.action;

import io.netty.channel.Channel;
import server.game.GameLogic;
import server.user.User;

public class Signup implements GameAction {
    private GameLogic gameLogic;

    public Signup(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void run(Channel incoming, String s) {
        User u = gameLogic.getUsers().signup(s);
        if (u == null) {
            incoming.writeAndFlush("Input error\n");
            return;
        }
        incoming.writeAndFlush("signed up, please login\n");
    }

    @Override
    public String getMethodName() {
        return "signup";
    }
}
