package shared.tree.like.object;

import shared.tools.MyXMLNode;

import java.util.HashMap;
import java.util.Map;

public class ObjectTL implements TreeLikeObject {
    Map<String, Object> map = new HashMap<>();

    @Override
    public void add(String name, Object obj) {
        map.put(name, obj);
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
