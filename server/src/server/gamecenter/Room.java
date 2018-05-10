package server.gamecenter;


import server.game.GameType;
import shared.events.Reactor;
import server.game.Game;
import server.user.User;
import shared.element.GameMap;

import java.util.ArrayList;
import java.util.List;


public class Room {
    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public enum Status {WAITING, PLAYING, PAUSED, OVER}

    private Status status = Status.WAITING;
    private String name;
    private GameMap gameMap;
    private Reactor reactor;
    private Game game;
    private List<User> users = new ArrayList<>();

    public Room(String name, Reactor reactor) {
        this.name = name;
        reactor.addSubReactor(GameType.EVENT_MAP.values(), this.reactor);
    }

    public void loadmap(String mapname) {
        this.gameMap = GameMap.loadMap(mapname);
    }

    public void startGame() {
        status = Status.PLAYING;
        game = new Game(reactor, gameMap);
        game.run();
    }

    public void stopGame() {
        game.stopGame();
    }
}
