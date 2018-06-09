package base.network;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import base.reactor.Reactor;

@ChannelHandler.Sharable
public class MessagePusher extends SimpleChannelInboundHandler<Object> {
    private Reactor reactor;

    public MessagePusher(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg.getClass());
        reactor.submitEvent(new ReceiveObjectEvent(msg, ctx.channel()));

    }
}
