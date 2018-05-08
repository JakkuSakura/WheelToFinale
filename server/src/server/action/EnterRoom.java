package server.action;

import io.netty.channel.Channel;
import server.room.ServerSample;
import server.user.User;

public class EnterRoom implements GameAction {
    private ServerSample serverSample;

    public EnterRoom(ServerSample serverSample) {
        this.serverSample = serverSample;
    }
    @Override
    public void run(Channel incoming, String s) {
        String[] spt = s.split(" ");
        User u = serverSample.getUsers().getPlayer("Addr", incoming.remoteAddress());
        if (u == null)
            return;
        u.enterRoom(serverSample.getRooms().getRoom(Integer.parseInt(spt[1])));
    }

    @Override
    public String getMethodName() {
        return "enterroom";
    }
}
