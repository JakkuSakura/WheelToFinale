package top.zhuoxinsocial.server.game;

import io.netty.channel.Channel;
import top.zhuoxinsocial.server.room.Rooms;
import top.zhuoxinsocial.server.user.User;
import top.zhuoxinsocial.server.user.Users;

import java.util.HashMap;
import java.util.Map;

public class GameLogic {
    private Users users;
    private Rooms rooms;
    private Map<String, GameImp> gameMethods = new HashMap<>();
    private GameImp[] gameImps = {new CreateRoom(this), new Login(this),
            new CreateRoom(this), new EnterRoom(this)};
    public GameLogic(Users users) {
        this.users = users;
        this.rooms = new Rooms();
        for (GameImp gameImp : gameImps) {
            gameMethods.put(gameImp.getMethodName(), gameImp);

        }
    }

    public Users getUsers() {
        return users;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void sendMessage(User user, String s) {
        user.sendMessage(s);
    }
    public void processMessage(Channel incoming, String s) {
        String[] spt = s.split(" ");
        if (gameMethods.containsKey(spt[0]))
            gameMethods.get(spt[0]).Action(incoming, s);

    }
    public void receiveMessage(User user, String s) {
        System.out.println("Received from " + user.getNickname());
        sendMessage(user, s);
    }

}
