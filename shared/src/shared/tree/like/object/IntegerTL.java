package shared.tree.like.object;

import shared.tools.MyXMLNode;

public class IntegerTL implements TreeLikeObject {
    int val = Integer.MIN_VALUE;

    @Override
    public void add(String name, Object obj) {
        val = Integer.parseInt((String) obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        try {
            node.getInt();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Type getType() {
        return Type.VLAUE;
    }

    @Override
    public void afterBuilding() {

    }
}
