package client.message;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageProcessors {
    private ThreadPoolExecutor threadPoolExecutor;
    private int threadNum;

    public MessageProcessors(int threadNum) {
        this.threadNum = threadNum;
        threadPoolExecutor = new ThreadPoolExecutor(0, threadNum,
                100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }

    public void addProcessor(MessageProcessor messageProcessor) {
        threadPoolExecutor.execute(messageProcessor);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

}
