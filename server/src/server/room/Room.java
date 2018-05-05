package server.room;


import server.map.GameMap;
import server.user.Player;

import java.util.ArrayList;
import java.util.List;

enum Status {WAITING, PLAYING, PAUSED, OVER}

public class Room implements Runnable {
    Status status = Status.WAITING;
    String name;
    GameMap gameMap;
    List<Player> players = new ArrayList<>();

    Thread thread;
    public Room(String name) {
        this.name = name;
    }

    public void loadmap(String mapname) {
        this.gameMap = GameMap.loadMap(mapname);
    }

    public void startGame() {
        status = Status.PLAYING;
        if (thread == null)
            thread = new Thread(this);
        if (!thread.isAlive())
            thread.start();
        System.out.println("Room " + name + " started");
    }

    @Override
    public void run() {
        wait4Start();

    }

    public void wait4Start() {
        while (status != Status.PLAYING || gameMap == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
