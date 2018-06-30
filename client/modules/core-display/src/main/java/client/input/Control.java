package client.input;

import base.reactor.Reactor;
import com.jme3.input.Input;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    public static final Map<String, Trigger> map = new HashMap<>();

    public static void initTriger(Class<? extends Input> inputClazz, Class<? extends Trigger> triggerClazz) {
        Field[] fields = inputClazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                map.put(field.getName(), triggerClazz.getDeclaredConstructor(int.class).newInstance(field.getInt(triggerClazz)));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    static {
        initTriger(KeyInput.class, KeyTrigger.class);
        initTriger(MouseInput.class, MouseButtonTrigger.class);

    }


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

        reactor.submitEvent(new KeyEvent(map.getOrDefault(name, new Trigger() {
            @Override
            public String getName() {
                return "Unregistered";
            }

            @Override
            public int triggerHashCode() {
                return -100086;
            }
        })));
    }

    public Reactor getReactor() {
        return reactor;
    }

    public static void main(String[] args) {

    }
}

