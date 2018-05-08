package server.events;

public abstract class EventHandler implements Comparable<EventHandler> {
    private PriorityLevel level = PriorityLevel.MEDIUM;

    @Override
    public int compareTo(EventHandler o) {
        return getLevel().compareTo(o.getLevel());
    }

    public PriorityLevel getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = PriorityLevel.of(level);
    }

    public abstract void handler(Event event);
}
