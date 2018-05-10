package main.java.server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;
import main.java.shared.events.Reactor;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

import static io.netty.util.internal.PlatformDependent.getClassLoader;


public class NetworkControl extends SimpleChannelInboundHandler<String>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Reactor reactor;

    public NetworkControl(ExecutorService threadPoolExecutor, Reactor reactor) {
        boolean directBufferFreeable = false;
        try {
            Class<?> cls = Class.forName("sun.nio.ch.DirectBuffer", false, getClassLoader(PlatformDependent.class));
            Method method = cls.getMethod("cleaner");
            if ("sun.misc.Cleaner".equals(method.getReturnType().getName())) {
                directBufferFreeable = true;
            }
        } catch (Throwable t) {
            // We don't have sun.nio.ch.DirectBuffer.cleaner().
        }
//        logger.debug("sun.nio.ch.DirectBuffer.cleaner(): {}", directBufferFreeable? "available" : "unavailable");

        this.reactor = new Reactor(threadPoolExecutor);
        reactor.addSubReactor(NetworkType.EVENT_MAP.values(), this.reactor);
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
        NetworkEvent networkEvent = new NetworkEvent(NetworkType.CHANNEL_READ_0, incoming);
        networkEvent.setData(msg);
        reactor.sendEvent(networkEvent);

    }
}