package server.action;

import io.netty.channel.Channel;

public interface GameAction {
    void run(Channel incoming, String s);
    String getMethodName();
}
