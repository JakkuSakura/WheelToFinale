package client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.network.MessagePusher;

public class ClientNetwork extends Thread {
    private String host;
    private int port;
    private EventLoopGroup group = new NioEventLoopGroup();
    private Bootstrap bootstrap = new Bootstrap();
    private boolean isRunning = true;
    private Channel channel;
    private int delay = 1000;
    private final Logger logger = LogManager.getRootLogger();
    public ClientNetwork(String host, int port, MessagePusher pusher) {
        logger.info("Init ClinetNetwork");
        this.host = host;
        this.port = port;
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new shared.network.NetworkInitializer(pusher, new Initializer()));

    }


    public void connect() {
        logger.info("Connecting server");
        isRunning = true;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopNetwork() {
        isRunning = false;
    }

    public void cleanup() {
        group.shutdownGracefully();

    }

    public void autoConnect() throws Exception {
        try {
            while (isRunning) {
                connect();
                Thread.sleep(delay);
            }
        }finally {
            cleanup();
        }
    }

    @Override
    public void run() {
        try {
            autoConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}