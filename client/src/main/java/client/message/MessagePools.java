package main.java.client.message;

import java.util.HashMap;
import java.util.Map;

public class MessagePools {
    private Map<Message.Type, MessagePool> messagePools;

    public MessagePools() {
        this.messagePools = new HashMap<>();
        for(Message.Type t : Message.Type.values()) {
            messagePools.put(t, new MessagePool());
        }
    }

    public MessagePool getMessagePool(Message.Type type) {
        return messagePools.get(type);
    }
}
