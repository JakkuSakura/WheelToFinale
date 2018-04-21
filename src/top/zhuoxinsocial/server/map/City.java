package top.zhuoxinsocial.server.map;

import top.zhuoxinsocial.server.user.Player;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class City {
    private Map values = new HashMap();
    static Map DEFAULT_VALUES = new HashMap(){
        {
            put("population", 10);
            put("defence", 10);
            put("wealth", 0);
            put("water", 100);
            put("water_supply", 100);
            put("owner", null);
        }
    };
    City() {
    }

    City set(String key, Object value) {
        values.put(key, value);
        return this;
    }
    Object get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            return DEFAULT_VALUES.get(key);
        }
    }

}
