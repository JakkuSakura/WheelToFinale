package base.events;

import base.reactor.EventHandler;

public abstract class Provider extends EventHandler {
    @Override
    public boolean check(Event event) {
        return event instanceof FetchEvent && check_0((FetchEvent) event);
    }

    public abstract boolean check_0(FetchEvent fetchEvent);
}
