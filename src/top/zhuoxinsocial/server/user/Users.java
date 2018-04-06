package top.zhuoxinsocial.server.user;

import top.zhuoxinsocial.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users {
    private List<User> users = new ArrayList<>();

    public User appendUser(String nickname, String email, String password) {
        User user = new User()
                .setNickname(nickname)
                .setEmail(email)
                .setPassword(password)
                .setUid(users.size());
        users.add(user);
        return user;
    }

    public User getUser(String by, Object v) throws Exception {
        for (User u : users) {
            Object val = Tools.invokeMethod(u, "get"+by, new Object[0]);
            if (Objects.equals(val, v))
                return u;
        }
        return User.getNullUser();
    }

    public User signup(String paragram) {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return User.getNullUser();
        return appendUser(spt[1], spt[2], spt[3]);
    }

    public User login(String paragram) throws Exception {
        String[] spt = paragram.split(" ");
        if (spt.length < 3)
            return User.getNullUser();
        User u = getUser("Email", spt[1]);
        if (!u.isNull() && u.equalPass(spt[2]))
        {
            return u;
        }
        else
        {
            return User.getNullUser();
        }
    }
}
