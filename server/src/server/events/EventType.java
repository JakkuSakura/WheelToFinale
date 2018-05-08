package server.events;


/**
 * This class is a modified menu class. Compare two by using ==.
 */
public class EventType  {
    public int getValue() {
        return value;
    }

    private final int value;
    protected EventType(int v) {
        value = v;
    }

}

