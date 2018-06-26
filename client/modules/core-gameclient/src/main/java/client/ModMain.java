package client;

import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import client.core.ModuleBase;
import client.core.StartClientEvent;
import client.display.event.StartDisplayEvent;
import client.network.NetworkStartEvent;

public class ModMain extends EventHandler implements ModuleBase {
    private Reactor rootReactor;
    private Reactor reactor;

    @Override
    public void handler(Chain chain, Event event) {
        rootReactor.submitEvent(new StartDisplayEvent());
        rootReactor.submitEvent(new NetworkStartEvent());
    }

    @Override
    public boolean check(Event event) {
        return event.convert(StartClientEvent.class).isPresent();
    }

    @Override
    public void init(Reactor reactor) {
        this.rootReactor = reactor;
        this.reactor = new Reactor(reactor, GameClientEvent.class);
        reactor.addHandler(StartClientEvent.class, this);
    }
}
