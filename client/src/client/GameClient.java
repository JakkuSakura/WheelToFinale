package client;

import client.input.Control;
import client.message.Message;
import client.message.MessagePools;
import client.message.MessageProcessor;
import client.message.MessageProcessors;
import client.network.Network;
import client.output.Display;
import client.output.Sounds;
import client.tools.*;

public class GameClient {
    private Control control = new Control();
    private MessagePools messagePools = new MessagePools();
    private MessageProcessors messageProcessors = new MessageProcessors(10);
    private Sounds sounds = new Sounds();
    private Display display = new Display(control);
    private Manager manager = new Manager();
    private Network network = new Network("0.0.0.0", 8888);
    public void run() throws Exception {
        display.start();
        network.autoConnect(1000);
        messageProcessors.addProcessor(new MessageProcessor(messagePools.getMessagePool(Message.Type.GAME), manager));
    }

    GameClient() {

    }

    public Control getControl() {
        return control;
    }

    public Display getDisplay() {
        return display;
    }

    public MessagePools getMessagePools() {
        return messagePools;
    }

    public Manager getManager() {
        return manager;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public Network getNetwork() {
        return network;
    }


    public static void main(String[] args) throws Exception {
        GameClient gameClient = new GameClient();
        gameClient.run();
    }

}
