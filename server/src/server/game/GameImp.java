package server.game;

import io.netty.channel.Channel;

public interface GameImp {
    void Action(Channel incoming, String s);
    String getMethodName();
}
