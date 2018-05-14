package shared.events;

public interface EventRunner<T extends Event> {
    void run(T event);
}
