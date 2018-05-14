package shared.reactor;

public class Priority implements Comparable<Priority> {
    public static final Priority LOW = Priority.of(0);
    public static final Priority MEDIUM = Priority.of(500);
    public static final Priority HIGH = Priority.of(1000);

    private int level;

    private Priority(int level) {
        this.level = level;
    }
    public static Priority of(int level) {
        return new Priority(level);
    }
    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(Priority o) {
        return -Integer.compare(getLevel(), o.getLevel());
    }
}
