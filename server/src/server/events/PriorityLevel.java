package server.events;

public class PriorityLevel implements Comparable<PriorityLevel> {
    public static final PriorityLevel LOW = PriorityLevel.of(0);
    public static final PriorityLevel MEDIUM = PriorityLevel.of(500);
    public static final PriorityLevel HIGH = PriorityLevel.of(1000);

    private int level;

    private PriorityLevel(int level) {
        this.level = level;
    }
    public static PriorityLevel of(int level) {
        return new PriorityLevel(level);
    }
    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(PriorityLevel o) {
        return -Integer.compare(getLevel(), o.getLevel());
    }
}
