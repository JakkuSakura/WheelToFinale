package main.java.client.message;

import java.util.Map;

public class MessageManager extends Thread {
    MessagePool messagePool;
    Map<Message.Type, MessageProcessor> processorMap;

    public MessageManager(MessagePool messagePool) {
        this.messagePool = messagePool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messagePool.pop();
                processorMap.get(message.getType()).receiveMessage(message);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
