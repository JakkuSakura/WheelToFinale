package server.eventhandler;

import io.netty.channel.Channel;
import server.GameServer;
import server.network.NetworkEvent;
import server.user.User;

public class Login extends NetworkHandler {
    private GameServer gameServer;

    public Login(GameServer gameServer) {
        super("Login");
        this.gameServer = gameServer;

    }

    @Override
    public void handler(NetworkEvent event) {
        Channel incoming = event.getChannel();
        String s = event.getData();
        try {
            User u = gameServer.getUserManager().login(s);
            if (u == null) {
                gameServer.getNetwork().sendMessage(incoming, "Password error");
                return;
            }
            incoming.writeAndFlush("logged in successfully");
            u.bindChannel(incoming);
        } catch (Exception ignored) {
        }
    }
}
