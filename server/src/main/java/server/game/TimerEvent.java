package server.game;

import base.events.Event;

public class TimerEvent extends Event {

    private Timer timer;

    public TimerEvent() {

    }
    public TimerEvent(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}

