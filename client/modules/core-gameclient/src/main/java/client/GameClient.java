package client;

import base.events.Event;
import base.network.MessagePusher;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;
import client.display.GameApp;
import client.display.event.ExitEvent;
import client.game.Games;
import client.input.Control;
import client.network.ClientNetwork;
import client.sounds.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class GameClient {
    private Reactor reactor = new Reactor();
    private Control control = new Control(reactor);
    private Sounds sounds = new Sounds();
    private GameApp gameApp = new GameApp(control);
    private Games games = new Games(reactor);
    private ClientNetwork clientNetwork = new ClientNetwork("0.0.0.0", 8888, new MessagePusher(reactor));
    private Logger logger = LogManager.getRootLogger();
    private boolean isRunning = false;

    public void run() throws Exception {
        logger.info("Running launcher.launcher");
        isRunning = true;
        clientNetwork.start();
        gameApp.start();
    }

    public GameClient() {
//        reactor.addHandler(ReceiveObjectEvent.class, new EventHandler() {
//            @Override
//            public void handler(Chain chain, Event event) {
//                ReceiveObjectEvent receiveObjectEvent = event.convert(ReceiveObjectEvent.class).get();
//                HelloMessage helloMessage = (HelloMessage) receiveObjectEvent.getObject();
//                System.out.println(helloMessage.getString());
//            }
//
//            @Override
//            public boolean check(Event event) {
//                ConvertOptional<Object> object = event.convert(ReceiveObjectEvent.class).map(ReceiveObjectEvent::getObject);
//                return object.convert(HelloMessage.class).isPresent();
//            }
//        });
    reactor.addHandler(ExitEvent.class, new EventHandler() {
            @Override
            public void handler(Chain chain, Event event) {
                stop();
            }

            @Override
            public boolean check(Event event) {
                return event instanceof ExitEvent;
            }
        });
    }

    public Control getControl() {
        return control;
    }

    public GameApp getGameApp() {
        return gameApp;
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
        clientNetwork.stopNetwork();
    }

    public void stop() {
        isRunning = false;
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
        gameClient.waitForStop();
    }

}
