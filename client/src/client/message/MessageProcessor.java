package client.message;

public class MessageProcessor implements Runnable {
    MessagePool messagePool;
    Receivable manager;

    public MessageProcessor(MessagePool messagePool, Receivable manager) {
        this.messagePool = messagePool;
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messagePool.pop();
                manager.receiveMessage(message);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
