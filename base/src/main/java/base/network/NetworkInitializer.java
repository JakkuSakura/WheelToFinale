package base.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class NetworkInitializer extends ChannelInitializer<SocketChannel> {
    private MessagePusher pusher;
    private SimpleChannelInboundHandler initializer;

    public NetworkInitializer(MessagePusher pusher, SimpleChannelInboundHandler initializer) {
        this.pusher = pusher;
        this.initializer = initializer;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //添加对象系列化编解码器, gzip压缩, 同时提供粘包拆包支持
        if (initializer != null)
            pipeline.addLast("initializer", initializer);
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast("decompressor", new GzipDecompressor());
        pipeline.addLast("decoder", new MessageDecoder());
        pipeline.addLast("pusher", pusher);

        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast("compressor", new GzipCompressor());
        pipeline.addLast("encoder", new MessageEncoder());


    }
}
