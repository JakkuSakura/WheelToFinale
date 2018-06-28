package client.core;

import base.reactor.Reactor;

@FunctionalInterface
public interface ModuleBase {
    void init(Reactor reactor);
}
