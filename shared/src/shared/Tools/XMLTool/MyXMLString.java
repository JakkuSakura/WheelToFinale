package shared.Tools.XMLTool;

public class MyXMLString implements MyXMLType {
    String str;

    @Override
    public Object get() {
        return str;
    }

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
}
