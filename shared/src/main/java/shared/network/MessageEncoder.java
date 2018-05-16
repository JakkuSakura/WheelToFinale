package shared.network;


import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import shared.utils.SerializeUtil;

import java.util.List;

//@ChannelHandler.Sharable
public class MessageEncoder extends MessageToMessageEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object obj, List<Object> buf) throws Exception {
        byte[] bytes = SerializeUtil.serializer(obj);
//        System.out.println("Encoded " + obj.getClass());
        buf.add(bytes);
    }

}