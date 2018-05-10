package main.java.server.network;

import main.java.shared.events.EventType;

public class PreSendObjectEvent extends main.java.shared.events.Event {
    public PreSendObjectEvent(EventType type) {
        super(NetworkType.SEND_DATA);
    }

}
