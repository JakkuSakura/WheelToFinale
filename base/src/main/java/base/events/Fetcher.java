package base.events;

public interface Fetcher<T> {
    void put(T obj);
}
