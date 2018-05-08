package server.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Reactor<T extends EventType> {
    private final Map<T, BlockingQueue<EventHandler>> handlers = new HashMap<>();
    private final BlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
    private final ExecutorService threadPoolExecutor;
    public Reactor(ExecutorService threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void addHandler(T type, EventHandler handler) {
        if (!getHandlers().containsKey(type))
            getHandlers().put(type, new PriorityBlockingQueue<>());
        getHandlers().get(type).add(handler);
    }

    public Map<T, BlockingQueue<EventHandler>> getHandlers() {
        return handlers;
    }

    public void addAllHandler(List<T> typeList, EventHandler handler) {
        typeList.forEach(eventType -> addHandler(eventType, handler));
    }

    public void sendEvent(Event event) {
        eventQueue.add(event);
    }

}
