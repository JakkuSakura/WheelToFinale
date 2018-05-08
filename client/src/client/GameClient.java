package client;

import client.game.Games;
import client.input.Control;
import client.message.*;
import client.network.Network;
import client.display.Display;
import client.sounds.Sounds;

public class GameClient {
    private Control control = new Control();
    private MessagePools messagePools = new MessagePools();
    private Sounds sounds = new Sounds();
    private Display display = new Display(control);
    private Games games = new Games(this);
    private MessageProcessor messageProcessor = new MessageProcessor();
    private Network network = new Network("0.0.0.0", 8888);
    private boolean isRunning = false;

    public void run() throws Exception {
        isRunning = true;
        display.start();
        network.start();
        waitForStop();
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

    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public Network getNetwork() {
        return network;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void cleanup() {

    }

    public void stop() {
        isRunning = false;
        network.stopNetwork();
    }

    public void waitForStop() {
        try {
            while (!isRunning) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

        } finally {
            cleanup();
        }
    }

    public static void main(String[] args) throws Exception {

        GameClient gameClient = new GameClient();
        gameClient.run();

    }

}
