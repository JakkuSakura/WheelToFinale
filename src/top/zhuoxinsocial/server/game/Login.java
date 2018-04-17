package top.zhuoxinsocial.server.game;

import top.zhuoxinsocial.server.user.User;

import io.netty.channel.Channel;

public class Login implements GameImp{
    private GameLogic gameLogic;

    public Login(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void Action(Channel incoming, String s) {
        try {
            User u = gameLogic.getUsers().login(s);
            if (u.isNull()) {
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
