package server.map;

import Tools.XMLTool.MyXMLNode;
import Tools.XMLTool.MyXMLType;
import Tools.XMLTool.XMLTool;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class GameMap implements MyXMLType {
    private Map<String, Object> values;
    private String mapname;

    private GameMap() {
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

        return (GameMap) Tools.XMLTool.XMLTool.readAsTree(xmlNode);

    }

    public static void main(String[] args) {
        GameMap gameMap = loadMap("maps/demo.xml");

    }

    public Cities getCity() {
        return (Cities) values.get("cities");
    }

    public Heroes getHeroes() {
        return (Heroes) values.get("heroes");
    }



    @Override
    public Object get() {
        return this;
    }

    @Override
    public void add(String name, Object obj) {
        values.put(name, obj);
    }

    public Landscape getLandscape() {
        return (Landscape) values.get("landscape");
    }

    public String getMapname() {
        return mapname;
    }

    public GameMap setMapname(String mapname) {
        this.mapname = mapname;
        return this;
    }

    @Override
    public boolean check(MyXMLNode node) {
        return true;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
