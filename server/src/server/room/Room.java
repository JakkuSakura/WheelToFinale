package server.room;


import server.game.Game;
import server.user.User;
import shared.map.GameMap;

import java.util.ArrayList;
import java.util.List;


public class Room {
    enum Status {WAITING, PLAYING, PAUSED, OVER}

    Status status = Status.WAITING;
    String name;
    GameMap gameMap;
    Game game = new Game();
    List<User> users = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void loadmap(String mapname) {
        this.gameMap = GameMap.loadMap(mapname);
    }

    public void startGame() {
        status = Status.PLAYING;

    }
}
