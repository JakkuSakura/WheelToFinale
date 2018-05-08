package server.game;

import io.netty.channel.Channel;
import server.action.CreateRoom;
import server.action.EnterRoom;
import server.action.GameAction;
import server.action.Login;
import server.room.Rooms;
import server.user.User;
import server.user.Users;

import java.util.HashMap;
import java.util.Map;

public class GameLogic {
    private Users users;
    private Rooms rooms;
    private Map<String, GameAction> gameMethods = new HashMap<>();
    private GameAction[] gameActions = {new CreateRoom(this), new Login(this),
            new CreateRoom(this), new EnterRoom(this)};
    public GameLogic(Users users) {
        this.users = users;
        this.rooms = new Rooms();
        for (GameAction gameAction : gameActions) {
            gameMethods.put(gameAction.getMethodName(), gameAction);

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
            gameMethods.get(spt[0]).run(incoming, s);

    }
    public void receiveMessage(User user, String s) {
        System.out.println("Received from " + user.get("nickname"));
        sendMessage(user, s);
    }

}
