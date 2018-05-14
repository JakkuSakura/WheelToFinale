package server.network;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import shared.SerializeUtils;

//开发自定编码器
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buf) throws Exception {
        byte[] bytes = SerializeUtils.serializer(obj);
        buf.writeBytes(bytes);
    }

}