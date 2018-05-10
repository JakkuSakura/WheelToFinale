package main.java.server.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public User addPlayer(User user) {
        users.add(user);
        return user;
    }

    public User getPlayer(String by, Object v) {
        for (User e : users) {
            if (Objects.equals(e.get("by"), v))
                return e;
        }
        return null;
    }

    public User signup(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return null;
        User user = new User()
                .set("nickname", spt[1])
                .set("email", spt[2])
                .set("password", spt[3])
                .set("uid", users.size());
        return addPlayer(user);
    }

    public User login(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return null;
        User u = getPlayer("Email", spt[1]);
        if (u == null || !u.equalPass(spt[2])) {
            return null;
        } else {
            return u;
        }
    }
}
