package server.action;

import io.netty.channel.Channel;
import server.game.GameLogic;
import server.user.User;

public class Login implements GameAction {
    private GameLogic gameLogic;

    public Login(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void run(Channel incoming, String s) {
        try {
            User u = gameLogic.getUsers().login(s);
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
