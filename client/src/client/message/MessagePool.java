package client.message;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessagePool {
    private BlockingQueue<Message> messages;

    public MessagePool() {
        messages = new LinkedBlockingQueue<>();
    }

    public void push(Message message) throws InterruptedException {
        messages.put(message);
    }

    public Message pop() throws InterruptedException {
        return messages.take();
    }

    public Message top() {
        return messages.peek();
    }


    public int getLength() {
        return messages.size();
    }
}
