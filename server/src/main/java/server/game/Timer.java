package main.java.server.game;

import main.java.shared.events.Event;
import main.java.shared.events.Reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Timer implements Runnable {
    public static final ExecutorService TIMER_THREAD_POOL = Executors.newSingleThreadExecutor();
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

    public long getRoundTime() {
        return roundTime;
    }

    public long getLastingTimeFromInit() {
        return currentTime - initTime;
    }

    public long getLastingTimeFromRound() {
        return currentTime - beginTime;
    }

    public void start() {
        TIMER_THREAD_POOL.submit(this);
    }
    @Override
    public void run() {
        currentTime = initTime = System.currentTimeMillis();

        reactor.sendEvent(new Event(GameType.GAME_BEGIN));
        while (game.isRunning()) {
            beginTime = System.currentTimeMillis();
            reactor.sendEvent(new Event(GameType.ROUND_BEGIN));
            while (game.isRunning() && currentTime - beginTime < roundTime) {
                currentTime = System.currentTimeMillis();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    game.stopGame();
                }
            }
            reactor.sendEvent(new Event(GameType.ROUND_END));
        }
        reactor.sendEvent(new Event(GameType.EVENT_BEGIN));

    }
}
