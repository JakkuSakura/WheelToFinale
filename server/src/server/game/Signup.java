package server.game;

import io.netty.channel.Channel;
import server.user.Player;

public class Signup implements GameImp{
    private GameLogic gameLogic;

    public Signup(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void Action(Channel incoming, String s) {
        Player u = gameLogic.getPlayers().signup(s);
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
