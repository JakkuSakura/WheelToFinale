package top.zhuoxinsocial;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class XMLTools {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document root;

    Document getRoot() {
        return root;
    }

    void printTree(int depth, Node root) {
        NodeList childNodes = root.getChildNodes();
        for (int k = 0; k < childNodes.getLength(); k++) {
            //区分出text类型的node以及element类型的node
            Node nd = childNodes.item(k);
            if (nd instanceof Element) {
                String fmt = "%" + (depth > 0 ? depth * 4 : "") + "s%s\n";
                System.out.format(fmt, "", nd.getNodeName());
                printTree(depth + 1, nd);
            }

        }
    }

    Node getChild(Node parent, String child) {
        NodeList nodeList = parent.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nd = nodeList.item(i);
            if (Objects.equals(nd.getNodeName(), child)) {
                return nd;
            }
        }
        return null;
    }

    Node getPath(String path) {
        return getPath(getRoot(), path.split("/"));
    }

    Node getPath(Node root, String path) {
        return getPath(root, path.split("/"));
    }

    Node getPath(Node root, String[] path) {
        if (path.length == 0 || root == null) {
            return root;
        } else {
            return getPath(getChild(root, path[0]), Arrays.copyOfRange(path, 1, path.length));
        }
    }

    void readXML(String file) {
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载文件到当前项目下
            root = db.parse(file);


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        XMLTools xmlTools = new XMLTools();
        xmlTools.readXML("maps/demo.xml");
        xmlTools.printTree(0, xmlTools.getRoot());
        Node height = xmlTools.getPath(xmlTools.getRoot(), "map/height");
        System.out.println(height.getNodeName());
    }
}
