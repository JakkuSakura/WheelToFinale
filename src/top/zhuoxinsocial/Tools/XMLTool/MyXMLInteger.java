package top.zhuoxinsocial.Tools.XMLTool;

public class MyXMLInteger implements MyXMLType {
    int val = Integer.MIN_VALUE;

    @Override
    public Object get() {
        return val;
    }

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
}
