package client.input;

import base.events.Event;
import com.jme3.input.controls.Trigger;

public class KeyEvent extends Event {


    private Trigger trigger;

    public KeyEvent(Trigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getKey() {
        return trigger;
    }
}
