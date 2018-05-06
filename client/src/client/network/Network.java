package client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class Network {
    private String host;
    private int port;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
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

            ChannelFuture f = bootstrap.connect(host, port).sync();
            Channel channel = f.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void autoConnect(int delay) throws Exception {
        while (true) {
            connect();
            Thread.sleep(delay);
        }
    }


}