package shared.map;

import shared.Tools.XMLTool.MyXMLNode;
import shared.Tools.XMLTool.MyXMLType;

import java.util.HashMap;

public class City implements MyXMLType {
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

    @Override
    public Object get() {
        return this;
    }

    @Override
    public void add(String name, Object obj) {
        put(name, obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return true;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
