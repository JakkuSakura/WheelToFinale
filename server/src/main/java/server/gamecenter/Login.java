package server.gamecenter;

import io.netty.channel.Channel;
import server.GameServer;
import server.network.NetworkStringEvent;
import server.user.User;

public class Login extends NetworkHandler<NetworkStringEvent> {
    private GameServer gameServer;

    public Login(GameServer gameServer) {
        super("Login");
        this.gameServer = gameServer;

    }

    @Override
    public void handler(NetworkStringEvent event) {
        Channel incoming = event.getChannel();
        String s = event.getData();
        try {
            User u = gameServer.getUserManager().login(s);
            if (u == null) {
                incoming.writeAndFlush("Password error" +"\r\n");
                return;
            }
            incoming.writeAndFlush("logged in successfully");
            u.bindChannel(incoming);
        } catch (Exception ignored) {
        }
    }
}
