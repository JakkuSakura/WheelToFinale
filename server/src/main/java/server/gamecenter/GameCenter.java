package server.gamecenter;


import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import org.slf4j.LoggerFactory;
import server.game.TimerAdder;
import server.game.TimerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class GameCenter extends EventHandler {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(GameCenter.class);
    private final List<Room> rooms = new ArrayList<>();
    private final Reactor reactor;
    private final TimerAdder timerAdder;

    public GameCenter(ExecutorService threadPool, Reactor parent) {
        logger.info("GameCenter init");
        reactor = new Reactor();
        parent.addSubReactor(Event.class, reactor);
        reactor.addHandler(StringCommand.class, this);
        timerAdder = new TimerAdder(threadPool);
        reactor.addHandler(TimerEvent.class, timerAdder);
    }

    public void createRoom(String name) {
        rooms.add(new Room(name, reactor));

    }

    public void startRoom(Integer name) {
        rooms.get(name).startGame();

    }

    public Room getRoom(Integer roomId) {
        return rooms.get(roomId);
    }

    public boolean command(String string) {
        try {
            String[] strings = string.split(" ");
            switch (strings[0]) {
                case "new_room":
                    createRoom(strings[0]);
                    break;
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public void handler(Chain chain, Event event) {
        StringCommand stringCommand = (StringCommand) event;
        String string = stringCommand.getCmd();
        command(string);
    }

    @Override
    public boolean check(Event event) {
        return event.convert(StringCommand.class).isPresent();
    }
}
