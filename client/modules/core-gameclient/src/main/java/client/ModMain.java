package client;

import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import client.core.ModuleBase;
import client.core.StartClientEvent;

public class ModMain extends EventHandler implements ModuleBase {
    private Reactor rootReactor;
    private Reactor reactor;
    private GameClient gameClient;
    @Override
    public void handler(Chain chain, Event event) {
           gameClient = new GameClient();
           gameClient.run();
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

    public Reactor getReactor() {
        return reactor;
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

    public GameClient getGameClient() {
        return gameClient;
    }

}
