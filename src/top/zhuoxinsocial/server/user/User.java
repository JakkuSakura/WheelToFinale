package top.zhuoxinsocial.server.user;


import io.netty.channel.*;
import top.zhuoxinsocial.server.room.Room;

import java.net.SocketAddress;
import java.util.Objects;

public class User {

    public int getUid() {
        return uid;
    }

    public User setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


    public User setNull(boolean aNull) {
        isNull = aNull;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean equalPass(String password) {
        return Objects.equals(this.password, password);
    }

    public boolean isNull() {
        return isNull;
    }

    static public User getNullUser() {
        if (nulluser == null) {
            nulluser = new User()
                    .setEmail("Null")
                    .setNickname("Null")
                    .setUid(-1)
                    .setNull(true);
        }
        return nulluser;
    }

    public User bindChannel(Channel channel) {
        this.channel = channel;
        this.addr = channel.remoteAddress();
        return this;
    }

    public void enterRoom(Room room)
    {
        this.nowRoom = room;
    }

    public Room getNowRoom()
    {
        return nowRoom;
    }

    public Channel getChannel() {
        return channel;
    }

    public void sendMessage(String s) {
        channel.writeAndFlush(s);
    }
    public SocketAddress getAddr()
    {
        return addr;
    }

    static private User nulluser;
    private int uid;
    private String nickname;
    private String email;
    private String password;
    private Channel channel;
    private SocketAddress addr;
    private Room nowRoom;
    private boolean isNull = false;

}
