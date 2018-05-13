package shared.events;

public class Event implements Cloneable {
    private final EventType type;
    private final String name;

    public Event(EventType type) {
        this(type, "Unnamed:"+type.getName());
    }

    public Event(EventType type, String name) {
        this.type = type;
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return type.toString() + ":" + name;
    }

    public Event clone() {
        try {
            return (Event) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
