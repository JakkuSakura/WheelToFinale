package top.zhuoxinsocial;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLTools {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public MyXMLNode readXML(String file) throws ParserConfigurationException, IOException, SAXException {
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载文件到当前项目下
        Node root = db.parse(file);
        return new MyXMLNode(root);

    }

    public static void main(String[] args) {

    }
}
