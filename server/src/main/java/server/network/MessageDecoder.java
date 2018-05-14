package server.network;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import shared.SerializeUtils;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> objects) throws Exception {
        int length = in.readableBytes();
        byte[] bytes = new byte[length];
        in.getBytes(in.readerIndex(), bytes,0,length);
        Object object = SerializeUtils.deserializer(bytes, CommonQueryObject.class);
        objects.add(object);

    }

}