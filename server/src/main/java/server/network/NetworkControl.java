package server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import shared.events.Reactor;

import java.util.Collection;
import java.util.concurrent.ExecutorService;


public class NetworkControl extends SimpleChannelInboundHandler<String>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Reactor reactor;

    public NetworkControl(ExecutorService threadPoolExecutor, Reactor reactor) {

        this.reactor = new Reactor(threadPoolExecutor);
        Collection<NetworkType> collection = NetworkType.EVENT_MAP.values();
        reactor.addSubReactor(collection, this.reactor);
//        this.reactor.addHandler(NetworkType.CHANNEL_READ_0, new JsonToObject());
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        channels.add(ctx.channel());
        reactor.sendEvent(new NetworkEvent(NetworkType.HANDLER_ADDED, incoming));

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        reactor.sendEvent(new NetworkEvent(NetworkType.HANDLER_REMOVED, incoming));

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        reactor.sendEvent(new NetworkEvent(NetworkType.CHANNEL_ACTIVE, incoming));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        reactor.sendEvent(new NetworkEvent(NetworkType.CHANNEL_INACTIVE, incoming));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        NetworkEvent networkEvent = new NetworkEvent(NetworkType.EXCEPTION_CAUGHT, incoming);
        reactor.sendEvent(networkEvent);
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel incoming = ctx.channel();
        NetworkStringEvent networkEvent = new NetworkStringEvent(NetworkType.CHANNEL_READ_0, incoming);
        networkEvent.setData(msg);
        reactor.sendEvent(networkEvent);

    }
}