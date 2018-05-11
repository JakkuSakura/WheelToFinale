package server.network;

import shared.events.Event;
import shared.events.EventType;

public class PreSendObjectEvent extends Event {
    public PreSendObjectEvent(EventType type) {
        super(NetworkType.SEND_DATA);
    }

}
