package server.game;

import base.element.GameMap;
import base.events.Event;
import base.reactor.Reactor;

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
        reactor.submitEvent(new TimerEvent(timer));
    }

    public Reactor getReactor() {
        return reactor;
    }


    public void stopGame() {
        isRunning = false;
    }
}
