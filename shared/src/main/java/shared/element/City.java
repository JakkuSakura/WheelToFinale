package shared.element;

import java.util.HashMap;

public class City {
    private final HashMap<String, Object> hashMap = new HashMap<>();

    public int size() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public boolean containsKey(String key) {
        return hashMap.containsKey(key);
    }

    public Object get(String key) {
        return hashMap.get(key);
    }

    public Object put(String key, Object value) {
        return hashMap.put(key, value);
    }

    public Object remove(String key) {
        return hashMap.remove(key);
    }

    public void clear() {
        hashMap.clear();
    }

}
