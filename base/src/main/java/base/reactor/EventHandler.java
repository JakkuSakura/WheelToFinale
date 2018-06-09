package base.reactor;

import base.events.Event;

import java.util.Objects;

public abstract class EventHandler implements Comparable<EventHandler> {

    private Priority level = Priority.MEDIUM;

    public abstract void handler(Chain chain, Event event);

    public abstract boolean check(Event event);

    public Priority getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Priority.of(level);
    }

    public int compareTo(EventHandler o) {
        return Objects.requireNonNull(o, "EventHandler cannot be null")
                .getLevel().compareTo(this.getLevel());
    }
}