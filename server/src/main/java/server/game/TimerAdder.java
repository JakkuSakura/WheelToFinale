package server.game;

import shared.events.Event;
import shared.reactor.Chain;
import shared.reactor.EventHandler;

import java.util.concurrent.ExecutorService;

public class TimerAdder extends EventHandler {
    private ExecutorService executorService;

    public TimerAdder(ExecutorService executorService) {
        this.executorService = executorService;
    }
    @Override
    public void handler(Chain chain, Event event) {
        TimerEvent timerEvent = (TimerEvent)event;
        this.executorService.submit(timerEvent.getTimer());
    }

    @Override
    public boolean check(Event event) {
        return event.convert(TimerEvent.class).isPresent();
    }
}
