package client.input;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import shared.reactor.Reactor;


public class Control implements ActionListener {
    private Reactor reactor;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final String SPACE = "SPACE";

    private void initKeys(InputManager inputManager) {
        // You can map one or several inputs to one named action
        inputManager.addMapping(UP, new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping(DOWN, new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping(SPACE, new KeyTrigger(KeyInput.KEY_SPACE));

        // Add the names to the action listener.
        inputManager.addListener(this, UP, DOWN, LEFT, RIGHT, SPACE);

    }

    public Control(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        KeyEvent.Keys key;

        switch (name) {
            case UP:
                key = KeyEvent.Keys.UP;
                break;
            case DOWN:
                key = KeyEvent.Keys.DOWN;
                break;
            case LEFT:
                key = KeyEvent.Keys.LEFT;
                break;
            case RIGHT:
                key = KeyEvent.Keys.RIGHT;
                break;
            case SPACE:
                key = KeyEvent.Keys.SPACE;
                break;
            default:
                key = KeyEvent.Keys.NONE;
                break;

        }

        reactor.submitEvent(new KeyEvent(key));
    }
}

