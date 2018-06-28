package client.display;

import base.reactor.Reactor;
import client.core.ModuleBase;
import client.display.event.DisplayEvent;


public class ModMain implements ModuleBase {
    private Reactor rootReactor;
    private Reactor reactor;
    @Override
    public void init(Reactor reactor) {
        this.rootReactor = reactor;
        this.reactor = new Reactor(rootReactor, DisplayEvent.class);
    }

    public Reactor getReactor() {
        return reactor;
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

}
