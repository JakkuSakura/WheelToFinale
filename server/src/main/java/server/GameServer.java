package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.gamecenter.GameCenter;
import server.network.ServerNetwork;
import server.user.UserManager;
import shared.events.ConvertOptional;
import shared.events.Event;
import shared.events.HelloMessage;
import shared.network.MessagePusher;
import shared.network.ReceiveObjectEvent;
import shared.reactor.Chain;
import shared.reactor.EventHandler;
import shared.reactor.Reactor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class GameServer {
    private final ThreadPoolExecutor gameThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private final Reactor reactor = new Reactor();
    private final ServerNetwork network = new ServerNetwork(8888, new MessagePusher(reactor));
    private final UserManager userManager = new UserManager();
    private final GameCenter gameCenter = new GameCenter(gameThreadPool);
    private final Logger logger = LogManager.getRootLogger();

    public GameServer() {
        reactor.addHandler(ReceiveObjectEvent.class, new EventHandler() {

            @Override
            public void handler(Chain chain, Event event) {
                ReceiveObjectEvent receiveObjectEvent = event.convert(ReceiveObjectEvent.class).get();
                HelloMessage helloMessage = (HelloMessage) receiveObjectEvent.getObject();
                System.out.println(helloMessage.getString());
                receiveObjectEvent.getChannel().writeAndFlush(new HelloMessage("Server"));
            }

            @Override
            public boolean check(Event event) {
                ConvertOptional<Object> object = event.convert(ReceiveObjectEvent.class).map(ReceiveObjectEvent::getObject);
                return object.convert(HelloMessage.class).isPresent();
            }

        });
        logger.info("Init GameServer");
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public GameCenter getGameCenter() {
        return gameCenter;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void run() throws Exception {
        logger.info("GameServer running");
        network.run();
    }

    public ServerNetwork getNetwork() {
        return network;
    }

    public void stopServer() throws InterruptedException {
        gameThreadPool.shutdown();
        gameThreadPool.awaitTermination(30, TimeUnit.SECONDS);
        gameThreadPool.shutdownNow();

    }

    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.run();
    }

}
