package client.display;

import com.jme3.scene.Spatial;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    public enum Type {
        SELECT
    }

    private Map<Type, Map<Spatial, Action>> map = new HashMap<>();

    public EventManager() {

    }

    public void register(Type type, Spatial spatial, Action action) {
        Map<Spatial, Action> m = map.computeIfAbsent(type, k -> new HashMap<>());
        m.put(spatial, action);
    }

    public void trigger(Type type, Spatial spatial) {
        Map<Spatial, Action> m = map.get(type);
        if (m == null) {
            return;
        }
        Action action = m.get(spatial);
        if (action != null) action.action(type);
        else if (spatial != spatial.getParent()) {
            trigger(type, spatial.getParent());
        }
    }
}
