package shared.reactor;

import shared.events.Event;

public class SubReactorEvent extends Event {
    private Event event;
    public SubReactorEvent(Event event) {
        super();
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
