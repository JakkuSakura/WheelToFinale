package server.game;

import io.netty.channel.Channel;
import server.room.Rooms;
import server.user.Player;
import server.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameLogic {
    private Players players;
    private Rooms rooms;
    private Map<String, GameImp> gameMethods = new HashMap<>();
    private GameImp[] gameImps = {new CreateRoom(this), new Login(this),
            new CreateRoom(this), new EnterRoom(this)};
    public GameLogic(Players players) {
        this.players = players;
        this.rooms = new Rooms();
        for (GameImp gameImp : gameImps) {
            gameMethods.put(gameImp.getMethodName(), gameImp);

        }
    }

    public Players getPlayers() {
        return players;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void sendMessage(Player player, String s) {
        player.sendMessage(s);
    }
    public void processMessage(Channel incoming, String s) {
        String[] spt = s.split(" ");
        if (gameMethods.containsKey(spt[0]))
            gameMethods.get(spt[0]).Action(incoming, s);

    }
    public void receiveMessage(Player player, String s) {
        System.out.println("Received from " + player.get("nickname"));
        sendMessage(player, s);
    }

}
