import org.junit.Test;
import base.events.Event;
import base.reactor.Chain;
import base.reactor.Reactor;
import base.reactor.EventHandler;

import static org.junit.Assert.assertEquals;


public class ReactorTest {
    @Test
    public void newReactor() {
        final int[] val = {0};
        Reactor reactor = new Reactor();
        EventHandler handler = new EventHandler() {
            @Override
            public boolean check(Event event) {
                return true;
            }

            @Override
            public void handler(Chain chain, Event event) {
                val[0] += 1;
            }
        };
        reactor.addHandler(Event.class, handler);
        reactor.submitEvent(new Event());
        assertEquals(val[0], 1);
    }

}
