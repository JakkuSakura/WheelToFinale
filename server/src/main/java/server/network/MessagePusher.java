package server.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import shared.reactor.Reactor;

public class MessagePusher extends SimpleChannelInboundHandler<Object> {
    private Reactor reactor;

    public MessagePusher(Reactor reactor) {

        this.reactor = reactor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        reactor.submitEvent(new ReceiveObjectEvent(msg, ctx.channel()));
    }
}
