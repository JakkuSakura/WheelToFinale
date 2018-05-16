package shared.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@SuppressWarnings("unchecked")
public class SerializeUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    public SerializeUtil() {
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }


    public static <T> byte[] serializer(T o) {
        Schema schema = getSchema(o.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] clazzBytes = o.getClass().getName().getBytes();
        byte[] bytes = ProtobufIOUtil.toByteArray(o, schema, buffer);
        byte[] finalBytes = new byte[clazzBytes.length + bytes.length + 1];
        try {
            if ((clazzBytes.length >= 127)) throw new RuntimeException("Class name too long");
            finalBytes[0] = (byte) clazzBytes.length;
            System.arraycopy(clazzBytes, 0, finalBytes, 1, clazzBytes.length);
            System.arraycopy(bytes, 0, finalBytes, clazzBytes.length + 1, bytes.length);
        } finally {
            buffer.clear();
        }
        return finalBytes;
    }

    public static <T> T deserializer(byte[] bytes) {
        T obj = null;
        try {
            byte length = bytes[0];
            byte[] className = Arrays.copyOfRange(bytes, 1, length + 1);
            byte[] classBody = Arrays.copyOfRange(bytes, length + 1, bytes.length);
            Class<?> clazz = Class.forName(new String(className));
            obj = (T) objenesis.newInstance(clazz);
            Schema schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(classBody, obj, schema);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
