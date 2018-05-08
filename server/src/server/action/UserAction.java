package server.action;

import io.netty.channel.Channel;
import server.room.ServerSample;

public class UserAction implements GameAction {

    private ServerSample serverSample;

    public UserAction(ServerSample serverSample) {
        this.serverSample = serverSample;
    }
    @Override
    public void run(Channel incoming, String s) {
    }

    @Override
    public String getMethodName() {
        return null;
    }
}
