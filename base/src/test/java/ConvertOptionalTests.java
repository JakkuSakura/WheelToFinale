import org.junit.Test;

public class ConvertOptionalTests {
    @Test
    public void convertTest() {
        long begin = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {

//            System.out.println(ConvertOptional.of(new Event()).convert(EmptyEvent.class).getClass());
        }
        System.out.println(end - begin);

    }
}
