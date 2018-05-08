package server.action;

import io.netty.channel.Channel;
import server.room.ServerSample;
import server.user.User;

public class Signup implements GameAction {
    private ServerSample serverSample;

    public Signup(ServerSample serverSample) {
        this.serverSample = serverSample;
    }
    @Override
    public void run(Channel incoming, String s) {
        User u = serverSample.getUsers().signup(s);
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
