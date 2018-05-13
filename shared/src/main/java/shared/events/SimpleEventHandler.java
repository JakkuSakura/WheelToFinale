package shared.events;

import java.util.Objects;

public abstract class SimpleEventHandler implements EventHandler {
    private Priority level = Priority.MEDIUM;

    public Priority getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Priority.of(level);
    }

    @Override
    public int compareTo(EventHandler o) {
        return Objects.requireNonNull(o, "EventHandler cannot be null")
                .getLevel().compareTo((o).getLevel());
    }
}
