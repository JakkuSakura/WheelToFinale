package client.network;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import shared.events.HelloMessage;
@ChannelHandler.Sharable
public class Initializer extends SimpleChannelInboundHandler<Void> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(new HelloMessage("client"));
        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Void msg) throws Exception {
        //nothing

    }
}
