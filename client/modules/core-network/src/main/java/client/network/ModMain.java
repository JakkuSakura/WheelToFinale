package client.network;

import base.events.Event;
import base.network.MessagePusher;
import base.network.NetworkEvent;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import client.core.ModuleBase;

public class ModMain extends EventHandler implements ModuleBase {

    private Reactor rootReactor;
    private Reactor reactor;
    private ClientNetwork network;

    @Override
    public void handler(Chain chain, Event event) {
        this.network = new ClientNetwork("0.0.0.0", 8888, new MessagePusher(reactor));
        this.network.start();
    }

    @Override
    public boolean check(Event event) {
        return event.convert(NetworkStartEvent.class).isPresent();
    }

    @Override
    public void init(Reactor reactor) {
        this.rootReactor = reactor;
        reactor.addHandler(NetworkStartEvent.class, this);
        this.reactor = new Reactor(reactor, NetworkEvent.class);
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

    public Reactor getReactor() {
        return reactor;
    }
}
