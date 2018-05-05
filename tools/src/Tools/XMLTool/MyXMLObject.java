package Tools.XMLTool;

import java.util.HashMap;
import java.util.Map;

public class MyXMLObject implements MyXMLType {
    Map<String, Object> map = new HashMap<>();

    @Override
    public Object get() {
        return map;
    }

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
}
