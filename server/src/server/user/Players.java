package server.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Players {
    private List<Player> players = new ArrayList<>();

    public Player addPlayer(Player player) {
        players.add(player);
        return player;
    }

    public Player getPlayer(String by, Object v) {
        for (Player e : players) {
            if (Objects.equals(e.get("by"), v))
                return e;
        }
        return null;
    }

    public Player signup(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return null;
        Player player = new Player()
                .set("nickname", spt[1])
                .set("email", spt[2])
                .set("password", spt[3])
                .set("uid", players.size());
        return addPlayer(player);
    }

    public Player login(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return null;
        Player u = getPlayer("Email", spt[1]);
        if (u == null || !u.equalPass(spt[2])) {
            return null;
        } else {
            return u;
        }
    }
}
