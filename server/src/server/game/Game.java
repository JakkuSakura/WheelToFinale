package server.game;

import server.events.Event;
import server.events.EventHandler;
import server.events.Reactor;
import shared.map.GameMap;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Game {
    private BlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();

    private boolean isRunning;
    private Reactor reactor;
    private GameMap gameMap;
    private Timer timer = new Timer(this);
    public Game(Reactor reactor, GameMap gameMap) {
        this.reactor = reactor;
        this.gameMap = gameMap;
    }
    public boolean isRunning() {
        return isRunning;
    }

    public void run() {
        this.isRunning = true;
        new Thread(timer).start();
        //rough
    }

    public Reactor getReactor() {
        return reactor;
    }


    public void stopGame() {
        isRunning = false;
    }
}

class GameInitHandler extends EventHandler {

    @Override
    public void handler(Event event) {

    }
}
//class GameInitHandler extends EventHandler {
//
//    @Override
//    public void handler(Event event) {
//
//    }
//}
//class GameInitHandler extends EventHandler {
//
//    @Override
//    public void handler(Event event) {
//
//    }
//}
//class GameInitHandler extends EventHandler {
//
//    @Override
//    public void handler(Event event) {
//
//    }
//}