package shared.tree.like.object;

import shared.tools.MyXMLNode;

public class StringTL implements TreeLikeObject {
    String str;

    @Override
    public void add(String name, Object obj) {
        str = (String) obj;
    }

    @Override
    public boolean check(MyXMLNode node) {
        return node.hasText();
    }

    @Override
    public Type getType() {
        return Type.VLAUE;
    }

    @Override
    public void afterBuilding() {

    }
}
