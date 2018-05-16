package client.game;

import client.message.GameEvent;
import shared.reactor.Reactor;

public class Games {

    private Reactor reactor = new Reactor();
    private int roomId;

    public Games(Reactor reactor) {
        this.reactor.setParentReactor(reactor);
        reactor.addSubReactor(GameEvent.class, this.reactor);
    }

    public Reactor getReactor() {
        return reactor;
    }

}
