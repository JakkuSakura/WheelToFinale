package base.events;

public class HelloMessage extends Event {
    private String string;

    public HelloMessage(String name) {
        string = "Hello, this is " + name;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
