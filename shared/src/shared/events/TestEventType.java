package shared.events;

public class TestEventType extends EventType{
    public static final TestEventType TEST = new TestEventType("TEST_TYPE");


    protected TestEventType(String name) {

        super(name);
    }
}
