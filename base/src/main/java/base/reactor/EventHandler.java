package base.reactor;

import base.events.Event;

public abstract class EventHandler implements Comparable<EventHandler> {

    public static int MEDIUM = 500;
    public static int HIGH = 0;
    public static int LOW  = 1000;

    private int level = MEDIUM;

    public abstract void handler(Chain chain, Event event);

    public abstract boolean check(Event event);

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int compareTo(EventHandler handler) {
        return Integer.compare(level, handler.level);
    }
}
