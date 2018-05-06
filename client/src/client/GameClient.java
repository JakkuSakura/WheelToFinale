package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class GameClient {
    private String host;
    private int port;
    private Input input;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
        input = new Input();
    }

    public static void main(String[] args) throws Exception {
        new GameClient("localhost", 8080).autoConnect();
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
                            pipeline.addLast(new GameClientHandler());
//                          pipeline.addLast(new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture f = bootstrap.connect(host, port).sync();
            Channel channel = f.channel();
            input.setChannel(channel);
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            input.setChannel(null);
        }
    }

    public void autoConnect() throws Exception {
        input.start();
        while (true) {
            System.out.println("Trying connecting");
            connect();
            Thread.sleep(3000);
        }
    }


}