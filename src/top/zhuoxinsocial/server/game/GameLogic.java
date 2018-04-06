package top.zhuoxinsocial.server.game;

import top.zhuoxinsocial.server.user.User;
import top.zhuoxinsocial.server.user.Users;

public class GameLogic {
    private Users users;

    public GameLogic(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    public void sendMessage(User user, String s) {
        user.sendMessage(s);
    }

    public void receiveMessage(User user, String s) {
        System.out.println("Received from " + user.getNickname());
//        sendMessage(user, s);
    }

}
