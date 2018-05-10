package shared.events;


import java.util.HashMap;
import java.util.Map;
/**
 * This class is a modified menu class. Compare two by using ==.
 */
public class EventType {

    public static final Map<String, EventType> EVENT_MAP = new HashMap<>();
    private Event template;
    private static int count = 0;

    private int value;
    private String name;
    protected EventType(String name) {
        this.name = name;
        value = count++;
        EVENT_MAP.put(toString(), this);
    }
    public String getGroup() {
        return getClass().getTypeName();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Event getTemplate() {
        return template.clone();
    }

    public void bindTemplate(Event bind) {
        template = bind;
    }

    public boolean check(Event event) {
        return event.getType() == this && this.getTemplate().getClass() == event.getClass();
    }

    public boolean checkChild(Event event) {
        return this.getClass().isAssignableFrom(event.getClass().getClass());
    }
    @Override
    public String toString() {
        return getGroup() + "." + getName();
    }
}

