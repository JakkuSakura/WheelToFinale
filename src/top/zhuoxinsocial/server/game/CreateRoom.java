package top.zhuoxinsocial.server.game;

import io.netty.channel.Channel;

public class CreateRoom implements GameImp {
    private GameLogic gameLogic;

    public CreateRoom(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void Action(Channel incoming, String s) {
        String[] spt = s.split(" ");
        if (gameLogic.getPlayers().getPlayer("Addr", incoming.remoteAddress()) != null)
            gameLogic.getRooms().createRoom(spt[1]);

    }

    @Override
    public String getMethodName() {
        return "createroom";
    }
}
