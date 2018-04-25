package top.zhuoxinsocial.server.map;

import org.xml.sax.SAXException;
import top.zhuoxinsocial.Tools.XMLTool.MyXMLNode;
import top.zhuoxinsocial.Tools.XMLTool.MyXMLType;
import top.zhuoxinsocial.Tools.XMLTool.XMLTool;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class GameMap implements MyXMLType {
    private Map<String, Object> values;
    private String mapname;

    public GameMap() {
    }


    public static GameMap loadMap(String filename) {
        XMLTool xmlTools = new XMLTool();
        MyXMLNode xmlNode;
        try {
            xmlNode = xmlTools.readXML(filename);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        xmlTools.setXMLClass(null);

        return (GameMap) XMLTool.readAsTree(xmlNode);

    }

    public static void main(String[] args) {
        GameMap gameMap = loadMap("maps/demo.xml");

    }

    @Override
    public Object get() {
        return this;
    }

    @Override
    public void add(String name, Object obj) {
        values.put(name, obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return false;
    }

    public String getMapname() {
        return mapname;
    }

    public GameMap setMapname(String mapname) {
        this.mapname = mapname;
        return this;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
