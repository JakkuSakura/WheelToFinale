package top.zhuoxinsocial;

import org.w3c.dom.*;

import java.util.Objects;

public class MyXMLNode implements Node {

    private final Node node;

    public MyXMLNode(Node node) {
        this.node = node;
    }

    public void printTree() {
        printTree(0);
    }

    public void printTree(int depth) {
        forEachNode((index, node) -> {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String fmt = "%" + (depth > 0 ? depth * 4 : "") + "s%s\n";
                System.out.format(fmt, "", node.getNodeName());
                node.printTree(depth + 1);
            }
        });
    }


    public MyXMLNode getNodeByPath(String path) {
        return getNodeByPath(path.split("/"), 0);
    }

    public MyXMLNode getNodeByPath(String[] path, int index) {
        if (path.length == index) {
            return this;
        } else if (index > path.length) {
            return null;
        } else {
            return getChild(path[index]).getNodeByPath(path, index + 1);
        }
    }


    public MyXMLNode getChild(String child) {
        String[] spt = child.split("[\\[|\\]]");
        final int[] count = {spt.length > 1 ? Integer.parseInt(spt[1]) : 0};
        final Node[] result = {null};
        forEachNode((index, node1) -> {
            if (count[0] >= 0) {
                if (Objects.equals(node1.getNodeName(), spt[0])) {
                    if (count[0] == 0)
                        result[0] = node1;
                    --count[0];
                }
            }
        });
        return new MyXMLNode(result[0]);
    }

    public String getText(String defaultValue) {
        return hasText() ? getFirstChild().getNodeValue() : defaultValue;
    }

    public int getInt(int defaultValue) {
        String text = getText(null);
        return text != null ? Integer.parseInt(text) : defaultValue;
    }

    public boolean hasText() {
        return getChildNodes().getLength() == 1 && getFirstChild().getNodeType() == Node.TEXT_NODE;
    }

    public interface ForeachNodeAction {
        void action(int index, MyXMLNode node);
    }

    public void forEachNode(ForeachNodeAction action) {
        NodeList nodeList = getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nd = nodeList.item(i);
            action.action(i, new MyXMLNode(nd));
        }
    }



    public String getNodeName() {
        return node.getNodeName();
    }

    public String getNodeValue() throws DOMException {
        return node.getNodeValue();
    }

    public void setNodeValue(String nodeValue) throws DOMException {
        node.setNodeValue(nodeValue);
    }

    public short getNodeType() {
        return node.getNodeType();
    }

    public Node getParentNode() {
        return node.getParentNode();
    }

    public NodeList getChildNodes() {
        return node.getChildNodes();
    }

    public Node getFirstChild() {
        return node.getFirstChild();
    }

    public Node getLastChild() {
        return node.getLastChild();
    }

    public Node getPreviousSibling() {
        return node.getPreviousSibling();
    }

    public Node getNextSibling() {
        return node.getNextSibling();
    }

    public NamedNodeMap getAttributes() {
        return node.getAttributes();
    }

    public Document getOwnerDocument() {
        return node.getOwnerDocument();
    }

    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        return node.insertBefore(newChild, refChild);
    }

    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        return node.replaceChild(newChild, oldChild);
    }

    public Node removeChild(Node oldChild) throws DOMException {
        return node.removeChild(oldChild);
    }

    public Node appendChild(Node newChild) throws DOMException {
        return node.appendChild(newChild);
    }

    public boolean hasChildNodes() {
        return node.hasChildNodes();
    }

    public Node cloneNode(boolean deep) {
        return node.cloneNode(deep);
    }

    public void normalize() {
        node.normalize();
    }

    public boolean isSupported(String feature, String version) {
        return node.isSupported(feature, version);
    }

    public String getNamespaceURI() {
        return node.getNamespaceURI();
    }

    public String getPrefix() {
        return node.getPrefix();
    }

    public void setPrefix(String prefix) throws DOMException {
        node.setPrefix(prefix);
    }

    public String getLocalName() {
        return node.getLocalName();
    }

    public boolean hasAttributes() {
        return node.hasAttributes();
    }

    public String getBaseURI() {
        return node.getBaseURI();
    }

    public short compareDocumentPosition(Node other) throws DOMException {
        return node.compareDocumentPosition(other);
    }

    public String getTextContent() throws DOMException {
        return node.getTextContent();
    }

    public void setTextContent(String textContent) throws DOMException {
        node.setTextContent(textContent);
    }

    public boolean isSameNode(Node other) {
        return node.isSameNode(other);
    }

    public String lookupPrefix(String namespaceURI) {
        return node.lookupPrefix(namespaceURI);
    }

    public boolean isDefaultNamespace(String namespaceURI) {
        return node.isDefaultNamespace(namespaceURI);
    }

    public String lookupNamespaceURI(String prefix) {
        return node.lookupNamespaceURI(prefix);
    }

    public boolean isEqualNode(Node arg) {
        return node.isEqualNode(arg);
    }

    public Object getFeature(String feature, String version) {
        return node.getFeature(feature, version);
    }

    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return node.setUserData(key, data, handler);
    }

    public Object getUserData(String key) {
        return node.getUserData(key);
    }
}
