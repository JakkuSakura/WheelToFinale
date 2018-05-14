package server.gamecenter;


import shared.reactor.Reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class GameCenter {
    private final List<Room> rooms = new ArrayList<>();
    private final Reactor reactor;

    public GameCenter(ExecutorService threadPool) {
        reactor = new Reactor();
    }

    public void createRoom(String name) {
        rooms.add(new Room(name, reactor));

    }

    public void startRoom(Integer name) {
        rooms.get(name).startGame();

    }

    public Room getRoom(Integer roomId) {
        return rooms.get(roomId);
    }
}
