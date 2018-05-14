package server.game;

import shared.element.GameMap;
import shared.events.Event;
import shared.reactor.Reactor;

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
