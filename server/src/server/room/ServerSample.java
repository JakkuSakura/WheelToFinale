package server.room;

import server.user.Users;

import java.util.concurrent.ExecutorService;

public class ServerSample {
    private final ExecutorService threadPoolExecutor;
    private final Users users = new Users();
    private final Rooms rooms;


    public ServerSample(ExecutorService threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
        rooms = new Rooms(threadPoolExecutor);
    }

    public Users getUsers() {
        return users;
    }

    public Rooms getRooms() {
        return rooms;
    }


}
