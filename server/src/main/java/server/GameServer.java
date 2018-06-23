package server;

import base.network.MessagePusher;
import base.reactor.Reactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.gamecenter.GameCenter;
import server.network.ServerNetwork;
import server.user.UserManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class GameServer {
    private final ThreadPoolExecutor gameThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private final Reactor reactor = new Reactor();
    private final ServerNetwork network = new ServerNetwork(8888, new MessagePusher(reactor));
    private final UserManager userManager = new UserManager();
    private final GameCenter gameCenter = new GameCenter(gameThreadPool, reactor);
    private static Logger logger = LoggerFactory.getLogger(GameServer.class);

    public GameServer() {
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
