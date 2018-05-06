package shared.Tools.XMLTool;

import java.util.ArrayList;
import java.util.List;

public class MyXMLArray implements MyXMLType {
    List<Object> list = new ArrayList<>();

    @Override
    public Object get() {
        return list;
    }

    @Override
    public void add(String name, Object obj) {
        list.add(obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return node.getXMLType() != null;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
