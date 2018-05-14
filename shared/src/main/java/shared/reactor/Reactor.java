package shared.reactor;

import shared.events.Event;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Reactor extends SimpleEventHandler<Event> {
    private final Map<Class<? extends Event>, Queue<EventHandler>> handlers = new ConcurrentHashMap<>();
    private Reactor parentReactor;

    public void setParentReactor(Reactor reactor) {
        this.parentReactor = reactor;
    }

    public void submitParent(Event event) {
        Objects.requireNonNull(parentReactor, "Didn't set parent Reactor").submitEvent(event);
    }

    public void addSubReactor(Class<? extends Event> eventClass, Reactor reactor) {
        addHandler(eventClass, reactor);
    }

    public void addHandler(Class<? extends Event> eventClass, EventHandler handler) {
        if (handlers.containsKey(eventClass))
            handlers.put(eventClass, new ConcurrentLinkedQueue<>());
        handlers.get(eventClass).add(handler);
    }

    public void removeHandler(Class<? extends Event> eventClass, EventHandler eventHandler) {
        handlers.get(eventClass).remove(eventHandler);
    }

    public void submitEvent(Event event) {
        Chain chain = new Chain(this);
        handlers.forEach((k, v) -> {
            if (k.isAssignableFrom(event.getClass()))
                v.forEach(h -> {
                    if (h.check(event))
                        chain.addHandler(h);
                });
        });
        chain.run(event);
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public void handler_0(Chain reactor, Event event) {
        submitEvent(event);
    }

}
