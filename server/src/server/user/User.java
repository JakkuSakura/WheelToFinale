package server.user;


import io.netty.channel.Channel;
import server.room.Room;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private final Map<String, Object> values = new HashMap<>();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User)
            return Objects.equals(get("uid"), ((User) obj).get("uid"));
        return false;
    }

    public boolean equalPass(String password) {
        return Objects.equals(get("password"), password);
    }


    public User bindChannel(Channel channel) {
        this.channel = channel;
        this.addr = channel.remoteAddress();
        return this;
    }

    public void enterRoom(Room room) {
        nowRoom = room;
    }

    public Room getNowRoom() {
        return nowRoom;
    }

    public Channel getChannel() {
        return channel;
    }

    public void sendMessage(String s) {
        channel.writeAndFlush(s);
    }

    public SocketAddress getAddr() {
        return addr;
    }

    private Channel channel;
    private SocketAddress addr;
    private Room nowRoom;

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean containsKey(String key) {
        return values.containsKey(key);
    }

    public Object get(String key) {
        return values.get(key);
    }

    public User set(String key, Object value) {
        values.put(key, value);
        return this;
    }

    public void clear() {
        values.clear();
    }

}
