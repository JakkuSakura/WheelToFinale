package top.zhuoxinsocial.server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import top.zhuoxinsocial.server.game.GameLogic;
import top.zhuoxinsocial.server.user.User;
import top.zhuoxinsocial.server.user.Users;

import java.net.InetSocketAddress;

@ChannelHandler.Sharable
public class GameServerControl extends SimpleChannelInboundHandler<String> { // (1)

    /**
     * A thread-safe Set  Using ChannelGroup, you can categorize Channels into a meaningful group.
     * A closed Channel is automatically removed from the collection,
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private GameLogic gameLogic;
    private Users users;

    public GameServerControl(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.users = this.gameLogic.getUsers();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {  // (2)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");

        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {  // (3)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");

        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) { // (4)
        Channel incoming = ctx.channel();
        gameLogic.processMessage(incoming, s);
        System.out.println(incoming.remoteAddress() + " said: " + s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) { // (5)
        Channel incoming = ctx.channel();
        incoming.writeAndFlush("Welcome\n");
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) { // (6)
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}