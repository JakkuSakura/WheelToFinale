package shared.map;

import shared.tools.MyXMLNode;
import shared.tree.like.object.TreeLikeObject;

import java.util.HashMap;
import java.util.Map;

public class Soldier implements TreeLikeObject {


    private final Map<String, Object> hashMap = new HashMap<>();

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

    public Soldier set(String key, Object value) {
        hashMap.put(key, value);
        return this;
    }

    public void clear() {
        hashMap.clear();
    }

    @Override
    public void add(String name, Object obj) {
        set(name, obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return true;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }

    @Override
    public void afterBuilding() {

    }
}
