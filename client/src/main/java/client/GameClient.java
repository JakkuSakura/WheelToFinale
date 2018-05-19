package client;

import client.display.Display;
import client.game.Games;
import client.input.Control;
import client.network.ClientNetwork;
import client.sounds.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.ConvertOptional;
import shared.events.Event;
import shared.events.HelloMessage;
import shared.network.MessagePusher;
import shared.network.ReceiveObjectEvent;
import shared.reactor.Chain;
import shared.reactor.EventHandler;
import shared.reactor.Reactor;

public class GameClient {
    private Reactor reactor = new Reactor();
    private Control control = new Control(reactor);
    private Sounds sounds = new Sounds();
    private Display display = new Display(control);
    private Games games = new Games(reactor);
    private ClientNetwork clientNetwork = new ClientNetwork("0.0.0.0", 8888, new MessagePusher(reactor));
    private Logger logger = LogManager.getRootLogger();
    private boolean isRunning = false;

    public void run() throws Exception {
        logger.info("Running client");
        isRunning = true;
//        clientNetwork.start();
        display.start();
        waitForStop();
    }

    GameClient() {
        reactor.addHandler(ReceiveObjectEvent.class, new EventHandler() {
            @Override
            public void handler(Chain chain, Event event) {
                ReceiveObjectEvent receiveObjectEvent = event.convert(ReceiveObjectEvent.class).get();
                HelloMessage helloMessage = (HelloMessage) receiveObjectEvent.getObject();
                System.out.println(helloMessage.getString());
            }

            @Override
            public boolean check(Event event) {
                ConvertOptional<Object> object = event.convert(ReceiveObjectEvent.class).map(ReceiveObjectEvent::getObject);
                return object.convert(HelloMessage.class).isPresent();
            }
        });
    }

    public Control getControl() {
        return control;
    }

    public Display getDisplay() {
        return display;
    }

    public Reactor getReactor() {
        return reactor;
    }


    public Sounds getSounds() {
        return sounds;
    }

    public ClientNetwork getClientNetwork() {
        return clientNetwork;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void cleanup() {

    }

    public void stop() {
        isRunning = false;
        clientNetwork.stopNetwork();
    }

    public void waitForStop() {
        logger.info("Waiting for stop");
        try {
            while (isRunning()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException ignored) {

        } finally {
            cleanup();
        }
    }

    public static void main(String[] args) throws Exception {

        GameClient gameClient = new GameClient();
        gameClient.run();

    }

}
