package top.zhuoxinsocial;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class XMLTools {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public MyXMLNode readXML(String file) throws ParserConfigurationException, IOException, SAXException {
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载文件到当前项目下
        Node root = db.parse(file);
        return new MyXMLNode(root);

    }

    public interface NodeAction {
        Object action(MyXMLNode node);
    }

    public static void putIntoMap(MyXMLNode node, String key, Map<String, Object> map, String errormessage, NodeAction action) {
        map.put(key, getValue(node, key, errormessage, action));

    }

    public static Object getValue(MyXMLNode node, String child, String errormessage, NodeAction action) {
        if (errormessage == null) {
            errormessage = "Cannot find '" + child + "'";
        }
        return action.action(Objects.requireNonNull(node.getChild(child), errormessage));

    }

    public static void main(String[] args) {

    }
}
