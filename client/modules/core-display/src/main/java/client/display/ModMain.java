package client.display;

import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import client.core.ModuleBase;
import client.display.event.DisplayEvent;
import client.display.event.StartDisplayEvent;
import client.input.Control;


public class ModMain extends EventHandler implements ModuleBase {
    private Reactor rootReactor;
    private Reactor reactor;
    @Override
    public void init(Reactor reactor) {
        this.rootReactor = reactor;
        this.reactor = new Reactor(rootReactor, DisplayEvent.class);
        this.reactor.addHandler(StartDisplayEvent.class, this);
    }

    @Override
    public void handler(Chain chain, Event event) {
        GameApp gameApp = new GameApp(new Control(this.rootReactor));
        gameApp.start();
    }

    @Override
    public boolean check(Event event) {
        return event.convert(StartDisplayEvent.class).isPresent();
    }

    public Reactor getReactor() {
        return reactor;
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

}
