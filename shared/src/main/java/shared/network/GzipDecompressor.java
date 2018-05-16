package shared.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import shared.utils.GzipUtil;

import java.util.List;

//@ChannelHandler.Sharable
public class GzipDecompressor extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        byte[] finalBytes = GzipUtil.uncompress(bytes);
//        System.out.println("Decompressed "+ length);

        out.add(finalBytes);

    }
}
