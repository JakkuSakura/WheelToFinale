package base.events;

public class Event implements Cloneable {
    @Override
    public String toString() {
        return getClass().getName();
    }

    public Event clone() {
        try {
            return (Event) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    public <T extends Event> ConvertOptional<T> convert(Class<T> convert) {
        return ConvertOptional.of(this).convert(convert);
    }
}
