package shared.events;

public interface Fetcher<T> {
    void put(T obj);
}
