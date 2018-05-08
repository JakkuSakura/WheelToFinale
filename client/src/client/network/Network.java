package client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class Network extends Thread {
    private String host;
    private int port;
    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    private boolean isRunning;
    Channel channel;
    int delay = 1000;
    public Network(String host, int port) {
        this.host = host;
        this.port = port;
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel arg0) {
                        ChannelPipeline pipeline = arg0.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new LengthFieldPrepender(2));
                        pipeline.addLast(new GameClientControl());
//                          pipeline.addLast(new HeartBeatReqHandler());
                    }
                });

    }


    public void connect() {
        isRunning = true;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void stopNetwork() {
        isRunning = false;
        channel.close();
    }

    public void cleanup() {

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