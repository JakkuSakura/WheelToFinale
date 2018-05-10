package shared.events;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

public class Reactor {
    private final Map<EventType, BlockingQueue<EventHandler>> handlers = new HashMap<>();
    private final Map<EventType, BlockingQueue<Reactor>> subReactors = new HashMap<>();
    private final BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
    private final ExecutorService threadPoolExecutor;
    private boolean selfExecutor;

    public Reactor(ExecutorService threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void selfExecutor(boolean selfExecutor) {
        this.selfExecutor = selfExecutor;
    }

    public void addSubReactor(Collection<? extends EventType> typeList, Reactor reactor) {
        typeList.forEach((type) -> {
            if (!subReactors.containsKey(type))
                subReactors.put(type, new LinkedBlockingQueue<>());
            try {
                subReactors.get(type).put(reactor);
            } catch (InterruptedException ignored) {
            }
        });
    }

    public void addHandler(EventType type, EventHandler handler) {
        if (!handlers.containsKey(type))
            handlers.put(type, new PriorityBlockingQueue<>());
        try {
            handlers.get(type).put(handler);
        } catch (InterruptedException ignored) {

        }
    }

    public void addHandler(EventType type, EventRunner runner) {
        addHandler(type, new SimpleEventHandler() {
            @Override
            public void handler(Event event) {
                runner.run(event);
            }

            @Override
            public String toString() {
                return "Unnamed handler in " + super.toString();
            }
        });
    }

    public BlockingQueue<EventHandler> getHandler(EventType type) {
        return handlers.get(type);
    }


    public void addAllHandler(Collection<? extends EventType> typeList, EventHandler handler) {
        typeList.forEach(eventType -> addHandler(eventType, handler));
    }

    public void addAllHandler(Collection<? extends EventType> typeList, EventRunner runner) {
        typeList.forEach(eventType -> addHandler(eventType, runner));
    }

    public void printTree(int i) {
        for (int j = 0; j < i; j++) {
            System.out.print("\t");
        }
        System.out.println(this);
        subReactors.forEach((k, v) -> {
            v.forEach(a -> a.printTree(i + 1));
        });
        handlers.forEach((k, v) -> v.forEach(handler -> {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("\t");
            }
            System.out.println(handler.toString());
        }));
    }

    public Future<?> sendEvent(Event event) {
        return threadPoolExecutor.submit(() -> call(event));
    }

    public Future<?> call(Event event) {
        Optional.ofNullable(handlers.get(event.getType())).ifPresent(hs -> hs.forEach(handler -> handler.handler(event)));
        Optional.ofNullable(subReactors.get(event.getType())).ifPresent(rs -> rs.forEach(reactor -> {
            if (reactor.selfExecutor) reactor.sendEvent(event);
            else reactor.call(event);
        }));
        return null;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Reactor reactor = new Reactor(executorService);
        Reactor reactor2 = new Reactor(executorService);
        reactor.addHandler(TestEventType.TEST, event -> System.out.println("H1"));
        reactor2.addHandler(TestEventType.TEST, event -> System.out.println("H2"));
        reactor2.selfExecutor(true);
        reactor.addSubReactor(TestEventType.EVENT_MAP.values(), reactor2);
//        reactor.printTree(0);
        reactor.sendEvent(new Event(TestEventType.TEST));
        System.out.println(executorService.getActiveCount());
    }

}
