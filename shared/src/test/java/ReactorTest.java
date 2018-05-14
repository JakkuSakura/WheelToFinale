import org.junit.Test;
import shared.events.Event;
import shared.reactor.Chain;
import shared.reactor.Reactor;
import shared.reactor.SimpleEventHandler;

import static org.junit.Assert.assertEquals;


public class ReactorTest {
    @Test
    public void newReactor() {
        final int[] val = {0};
        Reactor reactor = new Reactor();
        SimpleEventHandler<Event> handler = new SimpleEventHandler<Event>() {
            @Override
            public boolean check(Event event) {
                return true;
            }

            @Override
            public void handler_0(Chain chain, Event event) {
                val[0] += 1;
            }
        };
        reactor.addHandler(Event.class, handler);
        reactor.submitEvent(new Event());
        assertEquals(val[0], 1);
    }

}
