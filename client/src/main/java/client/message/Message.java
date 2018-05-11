package client.message;

public class Message {
    private String msg;
    private Type type;
    private int from;

    public Message() {
        type = Type.NONE;
    }
    public Message(Type type, int from, String msg) {
        this.type = type;
        this.from = from;
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public enum Type{NONE, GAME}

}
