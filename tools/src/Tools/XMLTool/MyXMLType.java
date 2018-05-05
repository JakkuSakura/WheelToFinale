package Tools.XMLTool;

public interface MyXMLType {

    Object get();

    void add(String name, Object obj);

    boolean check(MyXMLNode node);

    Type getType();

    enum Type {CONTAINER, VLAUE}
}
