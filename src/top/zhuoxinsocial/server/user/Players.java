package top.zhuoxinsocial.server.user;

import top.zhuoxinsocial.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Players {
    private List<Player> players = new ArrayList<>();

    public Player addPlayer(Player player) {

        players.add(player);
        return player;
    }

    public Player getPlayer(String by, Object v){
        for (Player u : players) {
            Object val;
            try {
                val = Tools.invokeMethod(u, "get"+by, new Object[0]);
            } catch (Exception e) {
                throw new Error(e);
            }
            if (Objects.equals(val, v))
                return u;
        }
        return Player.getNullUser();
    }

    public Player signup(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return Player.getNullUser();
        Player player = new Player()
                .setNickname(spt[1])
                .setEmail(spt[2])
                .setPassword(spt[3])
                .setUid(players.size());
        return addPlayer(player);
    }

    public Player login(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return Player.getNullUser();
        Player u = getPlayer("Email", spt[1]);
        if (!u.isNull() && u.equalPass(spt[2]))
        {
            return u;
        }
        else
        {
            return Player.getNullUser();
        }
    }
}
