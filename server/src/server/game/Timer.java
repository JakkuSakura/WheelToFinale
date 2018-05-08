package server.game;

import server.events.Event;
import server.events.EventType;
import server.events.Reactor;


public class Timer implements Runnable {
    private int roundTime = 60*1000;
    private Game game;
    private Reactor reactor;
    private long initTime;
    private long currentTime;
    private long beginTime;

    public Timer(Game game) {
        this.game = game;
        reactor = game.getReactor();
    }
    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void stop() {
    }

    @Override
    public void run() {
        initTime = System.currentTimeMillis();

        reactor.sendEvent(new Event(EventType.GAME_BEGIN));
        while (game.isRunning()) {
            beginTime = System.currentTimeMillis();
            reactor.sendEvent(new Event(EventType.ROUND_BEGIN));
            while (game.isRunning() && currentTime - beginTime < roundTime) {
                currentTime = System.currentTimeMillis();
            }
            reactor.sendEvent(new Event(EventType.ROUND_END));
        }
        reactor.sendEvent(new Event(EventType.EVENT_BEGIN));

    }
}
