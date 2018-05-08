package server.game;

import server.events.Event;
import server.events.EventHandler;
import server.events.Reactor;

public class Game {
    private boolean running;
    private Reactor reactor;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
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