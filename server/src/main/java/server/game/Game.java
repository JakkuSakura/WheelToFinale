package main.java.server.game;

import main.java.shared.events.Event;
import main.java.shared.events.SimpleEventHandler;
import main.java.shared.events.Reactor;
import main.java.shared.element.GameMap;

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

class GameInitHandlerSimple extends SimpleEventHandler {

    @Override
    public void handler(Event GameType) {

    }
}