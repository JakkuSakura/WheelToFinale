package client.message;

public class Message {
    String msg;
    private Type type;
    private int from;
    Message() {
        type = Type.NONE;
    }
    Message(Type type, int from, String msg) {
        this.type = type;
        this.from = from;
        this.msg = msg;
    }

    public enum Type{NONE, GAME}

}
