package shared.reactor;

import shared.events.Event;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class Chain {
    private Reactor reactor;
    private boolean stopNext = false;

    public Chain(Reactor reactor) {

        this.reactor = reactor;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public void addHandler(EventHandler handler) {
        eventHandlers.add(handler);
    }

    public void run(Event event) {
        stopNext = false;
        for(EventHandler handler : eventHandlers) {
//            if (handler.check(event))
            handler.handler(this, event);
            if (stopNext)
                break;
        }
    }

    public void stopNext() {
        stopNext = true;
    }
    private Queue<EventHandler> eventHandlers = new PriorityBlockingQueue<>();
}
