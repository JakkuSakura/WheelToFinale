package base.reactor;

import base.events.Event;

public abstract class SimpleEventHandler extends EventHandler {
    @Override
    public void handler(Chain chain, Event event) {
        handler0(event);
    }
    public abstract void handler0(Event event);

    @Override
    public boolean check(Event event) {
        return true;
    }
}
