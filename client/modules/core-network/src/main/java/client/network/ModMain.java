package client.network;

import base.reactor.Reactor;
import client.core.ModuleBase;

public class ModMain implements ModuleBase {

    private Reactor rootReactor;

    @Override
    public void init(Reactor reactor) {
        this.rootReactor = reactor;
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

}
