package server.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

public class Reactor {
    private Map<EventType, PriorityBlockingQueue<EventHandler>> handlers = new HashMap<>();

    public Reactor() {
        for (EventType type : EventType.values()) {
            handlers.put(type, new PriorityBlockingQueue<>());
        }
    }

    public void addHandler(EventType type, EventHandler handler) {
        handlers.get(type).add(handler);
    }
    public void addAllHandler(List<EventType> typeList, EventHandler handler) {
        typeList.forEach(eventType -> handlers.get(eventType).put(handler));
    }
    public void sendEvent(Event event) {
        handlers.get(event.getType()).forEach(handler -> handler.handler(event));
    }
}
