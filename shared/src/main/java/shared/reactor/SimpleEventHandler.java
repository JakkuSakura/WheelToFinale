package shared.reactor;

import shared.events.Event;

public abstract class SimpleEventHandler<T extends Event> extends EventHandler {

    @Override
    @SuppressWarnings("unchecked")
    public void handler(Chain chain, Event event) {
        handler_0(chain, (T) event);
    }

    public abstract boolean check(Event event);

    public abstract void handler_0(Chain chain, T event);

}
