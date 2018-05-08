package server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import server.events.Event;
import server.events.EventHandler;
import server.events.NetWorkType;
import server.events.Reactor;

import java.util.concurrent.ExecutorService;


public class NetworkControl extends SimpleChannelInboundHandler<String>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Reactor<NetWorkType> reactor;
    public NetworkControl(ExecutorService threadPoolExecutor) {
        reactor = new Reactor<>(threadPoolExecutor);
        reactor.addHandler(NetWorkType.HANDLER_ADDED, new EventHandler() {
            @Override
            public void handler(Event event) {

            }
        });
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();

        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {  // (3)
        Channel incoming = ctx.channel();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) { // (4)
        Channel incoming = ctx.channel();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) { // (5)
        Channel incoming = ctx.channel();
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