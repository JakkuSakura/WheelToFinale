package shared.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import shared.utils.GzipUtil;

//@ChannelHandler.Sharable
public class GzipCompressor extends MessageToByteEncoder<byte[]> {
    @Override
    public void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        byte[] finalBytes = GzipUtil.compress(msg);
//        System.out.println("Compressed "+ msg.length);
        out.writeBytes(finalBytes);
    }
}
