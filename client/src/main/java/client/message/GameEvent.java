package main.java.client.message;

public class GameEvent {
    public enum Type{NONE, VIEW, CCONTROL, DATA}
    Type type = Type.NONE;

    public void setType(Type type) {
        if (type == null)
            type = Type.NONE;
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
