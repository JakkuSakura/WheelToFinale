package server;

import server.events.Reactor;
import server.network.NetworkControl;
import server.network.ServerNetwork;
import server.room.ServerSample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
    private final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
    private final NetworkControl serverControl = new NetworkControl(threadPoolExecutor);
    private final ServerNetwork network = new ServerNetwork(8080, serverControl);
    private final Reactor reactor = new Reactor(threadPoolExecutor);
    private final ServerSample serverSample = new ServerSample(threadPoolExecutor);

    public ServerSample getServerSample() {
        return serverSample;
    }

    public void run() throws Exception {
        network.run();

    }

    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.run();
    }

}
