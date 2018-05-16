package shared.network;

import shared.events.Event;

public class CommonQueryObject extends Event {
    /**
     * userId = your userId
     * roomNumber =
     *  QueryServerInfo -1
     *  QueryGameCenterInfo -2
     *  QueryRoom room_id
     * operation =
     *  Query if you want to query
     *  Update if you 
     */
    private int userId;
    private String operation;
    private int roomNumber;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }
}
