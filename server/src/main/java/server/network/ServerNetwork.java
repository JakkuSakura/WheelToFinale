package server.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ServerNetwork {
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();
    private final int port;

    public ServerNetwork(int port, MessagePusher pusher) {

        this.port = port;
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        //添加对象系列化编解码器,同时提供粘包拆包支持
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                        pipeline.addLast("decoder", new MessageDecoder());

                        pipeline.addLast(new LengthFieldPrepender(2));
                        pipeline.addLast("encoder", new MessageEncoder());

                        pipeline.addLast("handler", pusher);

                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public void run() throws Exception {
        try {
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }
    }
}