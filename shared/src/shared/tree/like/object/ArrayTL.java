package shared.tree.like.object;

import shared.tools.MyXMLNode;

import java.util.ArrayList;
import java.util.List;

public class ArrayTL implements TreeLikeObject {
    List<Object> list = new ArrayList<>();

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

    @Override
    public void afterBuilding() {

    }
}
