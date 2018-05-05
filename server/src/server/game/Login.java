package server.game;

import io.netty.channel.Channel;
import server.user.Player;

public class Login implements GameImp{
    private GameLogic gameLogic;

    public Login(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void Action(Channel incoming, String s) {
        try {
            Player u = gameLogic.getPlayers().login(s);
            if (u == null) {
                incoming.writeAndFlush("Password error\n");
                return;
            }
            incoming.writeAndFlush("logged in successfully\n");
            u.bindChannel(incoming);
        } catch (Exception ignored) {
        }
    }

    @Override
    public String getMethodName() {
        return "login";
    }
}
