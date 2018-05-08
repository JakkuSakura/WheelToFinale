package shared.tree.like.object;

import shared.tools.MyXMLNode;

public interface TreeLikeObject {

    void add(String name, Object obj);

    boolean check(MyXMLNode node);

    Type getType();

    void afterBuilding();
    enum Type {CONTAINER, VLAUE}
}
