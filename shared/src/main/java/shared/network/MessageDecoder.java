package shared.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import shared.utils.SerializeUtil;

import java.util.List;

//@ChannelHandler.Sharable
public class MessageDecoder extends MessageToMessageDecoder<byte[]> {

    @Override
    protected void decode(ChannelHandlerContext ctx, byte[] in, List<Object> objects) throws Exception {
        Object object = SerializeUtil.deserializer(in);
//        System.out.println("Decoded" + object.getClass());
        objects.add(object);
    }

}