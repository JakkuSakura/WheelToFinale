package shared.events;

public interface EventHandler extends Comparable<EventHandler> {
    void handler(Event event);
    Priority getLevel();

}
