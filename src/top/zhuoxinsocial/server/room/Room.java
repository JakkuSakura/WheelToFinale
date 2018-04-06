package top.zhuoxinsocial.server.room;

import top.zhuoxinsocial.server.map.Map;
import top.zhuoxinsocial.server.user.User;

import java.util.ArrayList;
import java.util.List;

public class Room {
    String name;
    Map map;
    List<User> players = new ArrayList<>();
}
