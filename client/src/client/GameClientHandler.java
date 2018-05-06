package client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 


public class GameClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String s) {
		System.out.println(s);
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) { // (6)
		Channel incoming = ctx.channel();
		System.out.println("断开");
        ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) { // (5)
		Channel incoming = ctx.channel();
		System.out.println("连接到：" + incoming.remoteAddress());
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Channel incoming = ctx.channel();
		System.out.println("ChatServer:"+incoming.remoteAddress()+"异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
