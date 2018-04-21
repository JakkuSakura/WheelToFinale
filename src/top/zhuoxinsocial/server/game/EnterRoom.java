package top.zhuoxinsocial.server.game;

import io.netty.channel.Channel;
import top.zhuoxinsocial.server.user.Player;

public class EnterRoom implements GameImp{
    private GameLogic gameLogic;

    public EnterRoom(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    @Override
    public void Action(Channel incoming, String s) {
        String[] spt = s.split(" ");
        Player u = gameLogic.getPlayers().getPlayer("Addr", incoming.remoteAddress());
        if (u == null)
            return;
        u.enterRoom(gameLogic.getRooms().getRoom(spt[1]));
    }

    @Override
    public String getMethodName() {
        return "enterroom";
    }
}
