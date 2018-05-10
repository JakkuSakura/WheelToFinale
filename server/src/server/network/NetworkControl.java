package server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import shared.events.Reactor;

import java.util.concurrent.ExecutorService;


public class NetworkControl extends SimpleChannelInboundHandler<String>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Reactor reactor;
    public NetworkControl(ExecutorService threadPoolExecutor, Reactor reactor) {
        reactor = new Reactor(threadPoolExecutor);
        this.reactor = new Reactor(threadPoolExecutor);
        reactor.addSubReactor(NetWorkType.EVENT_MAP.values(), this.reactor);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        Channel incoming = ctx.channel();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        reactor.sendEvent(NetWorkType.CHANNEL_ACTIVE.getTemplate());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) { // (6)
        Channel incoming = ctx.channel();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();

        ctx.close();
    }


}