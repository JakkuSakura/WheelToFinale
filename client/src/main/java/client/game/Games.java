package main.java.client.game;

import main.java.client.GameClient;
import main.java.client.message.Message;
import main.java.client.message.MessageManager;

public class Games {
    private MessageManager messageManager;
    private GameClient gameClient;
    private int roomId;
    public Games(GameClient client) {
        this.gameClient = client;
        this.messageManager = new MessageManager(client.getMessagePools().getMessagePool(Message.Type.GAME));
    }

}
