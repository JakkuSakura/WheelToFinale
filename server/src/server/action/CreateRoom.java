package server.action;

import io.netty.channel.Channel;
import server.room.ServerSample;

public class CreateRoom implements GameAction {
    private ServerSample serverSample;

    public CreateRoom(ServerSample serverSample) {
        this.serverSample = serverSample;
    }

    @Override
    public void run(Channel incoming, String s) {
        String[] spt = s.split(" ");
        if (serverSample.getUsers().getPlayer("Addr", incoming.remoteAddress()) != null)
            serverSample.getRooms().createRoom(spt[1]);

    }

    @Override
    public String getMethodName() {
        return "createroom";
    }
}
