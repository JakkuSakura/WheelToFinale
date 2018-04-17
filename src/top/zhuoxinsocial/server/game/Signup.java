package top.zhuoxinsocial.server.game;

import io.netty.channel.Channel;
import top.zhuoxinsocial.server.user.User;

public class Signup implements GameImp{
    private GameLogic gameLogic;

    public Signup(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void Action(Channel incoming, String s) {
        User u = gameLogic.getUsers().signup(s);
        if (u.isNull()) {
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
