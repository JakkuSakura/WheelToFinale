package client.input;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import base.reactor.Reactor;

import java.util.HashMap;
import java.util.Map;

public class Control implements ActionListener {
    private Reactor reactor;
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String SPACE = "SPACE";
    public static final String LMB = "LMB";
    public static final String RMB = "RMB";
    public static final Map<String, Trigger> map = new HashMap<String, Trigger>() {
        {
            put(UP, new KeyTrigger(KeyInput.KEY_UP));
            put(DOWN, new KeyTrigger(KeyInput.KEY_DOWN));
            put(LEFT, new KeyTrigger(KeyInput.KEY_LEFT));
            put(RIGHT, new KeyTrigger(KeyInput.KEY_RIGHT));
            put(SPACE, new KeyTrigger(KeyInput.KEY_SPACE));
            put(LMB, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
            put(RMB, new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        }
    };

    public void initKeys(InputManager inputManager) {
        map.forEach(inputManager::addMapping);
        inputManager.addListener(this, map.keySet().toArray(new String[0]));


    }

    public Control(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (!isPressed)
            return;
        System.out.println(name);
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
            case LMB:
                key = KeyEvent.Keys.LMB;
                break;
            case RMB:
                key = KeyEvent.Keys.RMB;
                break;

            default:
                key = KeyEvent.Keys.NONE;
                break;

        }

        reactor.submitEvent(new KeyEvent(key));
    }

    public Reactor getReactor() {
        return reactor;
    }
}

