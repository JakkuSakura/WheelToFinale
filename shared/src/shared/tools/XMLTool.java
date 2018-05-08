package shared.tools;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import shared.tree.like.object.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class XMLTool {

    private static Map<String, Class<? extends TreeLikeObject>> defaultClassMap = new HashMap<String, Class<? extends TreeLikeObject>>() {{
        put("int", IntegerTL.class);
        put("String", StringTL.class);
        put("Array", ArrayTL.class);
        put("Object", ObjectTL.class);
    }};
    private static Map<String, Class<? extends TreeLikeObject>> xmlClassMap = defaultClassMap;
    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static void putIntoMap(MyXMLNode node, String key, Map<String, Object> map, String errormessage, NodeAction action) {
        map.put(key, getValue(node, key, errormessage, action));

    }

    public static Object getValue(MyXMLNode node, String child, String errormessage, NodeAction action) {
        if (errormessage == null) {
            errormessage = "Cannot find '" + child + "'";
        }
        return action.action(Objects.requireNonNull(node.getChild(child), errormessage));

    }

    @SuppressWarnings("unchecked")
    public static TreeLikeObject newItem(MyXMLNode node) {
        try {
            Class<? extends TreeLikeObject> clazz;

            if (xmlClassMap.containsKey(node.getXMLType()))
                clazz = xmlClassMap.get(node.getXMLType());
            else {
                Class cls = Class.forName(node.getXMLType());
                if (TreeLikeObject.class.isAssignableFrom(cls))
                    clazz = cls;
                else
                    throw new Exception("Your class " + cls.getName() + " is not a subclass of " + TreeLikeObject.class.getName());
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static TreeLikeObject readAsTree(MyXMLNode curentNode) {
        if (curentNode.getNodeType() == Node.DOCUMENT_NODE)
            return readAsTree(new MyXMLNode(curentNode.getFirstChild()));

        TreeLikeObject currentXMLType = newItem(curentNode);

        curentNode.forEachNode(Node.ELEMENT_NODE, (index, node) -> {
            if (!currentXMLType.check(node))
                return;
            TreeLikeObject childXMLType = newItem(node);
            switch (childXMLType.getType()) {
                case CONTAINER:
                    Optional.ofNullable(readAsTree(node))
                            .ifPresent(t -> {
                                        childXMLType.add(node.getNodeName(), t);
                                        currentXMLType.add(node.getNodeName(), childXMLType);
                                    }
                            );
                    break;
                case VLAUE:
                    childXMLType.add(node.getNodeName(), node.getText());
                    currentXMLType.add(node.getNodeName(), childXMLType);
                    break;
            }

        });
        return currentXMLType;


    }

    public MyXMLNode readXML(String file) throws ParserConfigurationException, IOException, SAXException {
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载文件到当前项目下
        Node root = db.parse(file);
        return new MyXMLNode(root);

    }

    public void setXMLClass(Map<String, Class<? extends TreeLikeObject>> xmlClassMap) {
        if (xmlClassMap != null) {
            XMLTool.xmlClassMap = new HashMap<>(xmlClassMap);
            XMLTool.xmlClassMap.putAll(defaultClassMap);
        } else
            XMLTool.xmlClassMap = defaultClassMap;
    }


    public interface NodeAction {
        Object action(MyXMLNode node);
    }
}
