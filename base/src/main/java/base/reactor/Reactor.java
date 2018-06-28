package base.reactor;

import base.events.Event;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Reactor extends EventHandler {
    private final Map<Class<? extends Event>, Queue<EventHandler>> handlers = new ConcurrentHashMap<>();
    private Class<? extends Event> eventType;

    private Reactor parentReactor;

    public Reactor(Reactor parentReactor, Class<? extends Event> eventType) {
        if (parentReactor != null)
            parentReactor.addSubReactor(eventType, this);
        this.eventType = eventType;
    }

    public Reactor() {
        this(null, Event.class);
    }

    public Reactor(Class<? extends Event> eventType) {
        this(null, eventType);
    }

    public Class<? extends Event> getEventType() {
        return eventType;
    }

    public void setParentReactor(Reactor reactor) {
        reactor.addSubReactor(getEventType(), this);
    }

    public void addSubReactor(Class<? extends Event> eventClass, Reactor reactor) {
        reactor.parentReactor = this;
        addHandler(eventClass, reactor);
    }

    public void submitParent(Event event) {
        Objects.requireNonNull(parentReactor, "Didn't set parent Reactor").submitEvent(event);
    }

    public Reactor getParentReactor() {
        return parentReactor;
    }

    public void addHandler(SimpleEventHandler eventHandler) {
        addHandler(Event.class, eventHandler);
    }
    public void addHandler(Class<? extends Event> eventClass, EventHandler handler) {
        Queue<EventHandler> handlers = this.handlers.get(eventClass);
        if (handlers == null)
            this.handlers.put(eventClass, new ConcurrentLinkedQueue<>());
        this.handlers.get(eventClass).add(handler);
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
        return event.getClass() == getEventType();
    }

    @Override
    public void handler(Chain reactor, Event event) {
        submitEvent(event);
    }

}
