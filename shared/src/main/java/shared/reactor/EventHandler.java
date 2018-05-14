package shared.reactor;

import shared.events.Event;

import java.util.Objects;

public abstract class EventHandler implements Comparable<EventHandler> {
    private Priority level = Priority.MEDIUM;
    private EventHandler nextHandler;
    public Priority getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Priority.of(level);
    }

    public int compareTo(EventHandler o) {
        return Objects.requireNonNull(o, "EventHandler cannot be null")
                .getLevel().compareTo((o).getLevel());
    }

    public abstract void handler(Chain chain, Event event);

    public abstract boolean check(Event event);
}
