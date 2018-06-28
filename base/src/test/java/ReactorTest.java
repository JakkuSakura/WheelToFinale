import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import org.junit.Test;

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

    static public class Ev extends Event {

    }
    @Test
    public void subReactor() {
        Reactor root = new Reactor();
        Reactor sub = new Reactor(root, Ev.class);
        sub.addHandler(Ev.class, new EventHandler() {
            @Override
            public void handler(Chain chain, Event event) {
                System.out.println("FUCK");
            }

            @Override
            public boolean check(Event event) {
                return event.convert(Ev.class).isPresent();
            }
        });
        root.submitEvent(new Ev());
    }


}
