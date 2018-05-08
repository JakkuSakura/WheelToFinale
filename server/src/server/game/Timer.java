package server.game;

import server.events.Event;
import server.events.GameType;
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

    public long getRoundTime() {
        return roundTime;
    }

    public long getLastingTimeFromInit() {
        return currentTime - initTime;
    }

    public long getLastingTimeFromRound() {
        return currentTime - beginTime;
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
            }
            reactor.sendEvent(new Event(GameType.ROUND_END));
        }
        reactor.sendEvent(new Event(GameType.EVENT_BEGIN));

    }
}
