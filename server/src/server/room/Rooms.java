package server.room;

import java.util.HashMap;
import java.util.Map;

public class Rooms {
    private Map<String, Room> rooms = new HashMap<>();
    public Rooms()
    {

    }
    public void createRoom(String name)
    {
        rooms.put(name, new Room(name));

    }
    public void startRoom(String name)
    {
        rooms.get(name).startGame();

    }

    public Room getRoom(String roomname) {
        return rooms.get(roomname);
    }
}
