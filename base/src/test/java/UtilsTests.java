import org.junit.Test;
import base.utils.GzipUtil;
import base.utils.SerializeUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UtilsTests {
    @Test
    public void serializeTest() {
        try {
            String str = "Hello";
            byte[] bytes = SerializeUtil.serializer(str);


            String str2 = SerializeUtil.deserializer(bytes);

            assertEquals(str, str2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void gzipTest() {
        try {
            byte[] bytes = "Test".getBytes();
            byte[] compressed = GzipUtil.compress(bytes);
            byte[] decompressed = GzipUtil.uncompress(compressed);
            assertArrayEquals(bytes, decompressed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
