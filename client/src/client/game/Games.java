package client.game;

import client.GameClient;
import client.message.Message;
import client.message.MessageManager;

public class Games {
    private MessageManager messageManager;
    private GameClient gameClient;
    private int roomId;
    public Games(GameClient client) {
        this.gameClient = client;
        this.messageManager = new MessageManager(client.getMessagePools().getMessagePool(Message.Type.GAME));
    }

}
