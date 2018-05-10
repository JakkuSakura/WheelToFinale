package server;

import server.gamecenter.GameCenter;
import server.network.NetworkControl;
import server.network.ServerNetwork;
import server.user.UserManager;
import shared.events.Reactor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private final ThreadPoolExecutor networkThreadPool = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
    private final ThreadPoolExecutor gameThreadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
    private final Reactor reactor = new Reactor(networkThreadPool);
    private final NetworkControl serverControl = new NetworkControl(networkThreadPool, reactor);
    private final ServerNetwork network = new ServerNetwork(8080, serverControl);
    private final UserManager userManager = new UserManager();
    private final GameCenter gameCenter = new GameCenter(gameThreadPool);

    public GameServer() {
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public GameCenter getGameCenter() {
        return gameCenter;
    }

    public void run() throws Exception {
        network.run();
    }

    public ServerNetwork getNetwork() {
        return network;
    }

    public void stooServer() throws InterruptedException {
        networkThreadPool.shutdown();
        networkThreadPool.awaitTermination(30, TimeUnit.SECONDS);
        networkThreadPool.shutdownNow();

        gameThreadPool.shutdown();
        gameThreadPool.awaitTermination(30, TimeUnit.SECONDS);
        gameThreadPool.shutdownNow();

    }

    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.run();
    }

}
