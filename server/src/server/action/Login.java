package server.action;

import io.netty.channel.Channel;
import server.room.ServerSample;
import server.user.User;

public class Login implements GameAction {
    private ServerSample serverSample;

    public Login(ServerSample serverSample) {
        this.serverSample = serverSample;
    }
    @Override
    public void run(Channel incoming, String s) {
        try {
            User u = serverSample.getUsers().login(s);
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
