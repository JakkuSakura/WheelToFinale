package shared.map;

import org.xml.sax.SAXException;
import shared.tools.MyXMLNode;
import shared.tree.like.object.TreeLikeObject;
import shared.tools.XMLTool;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class GameMap implements TreeLikeObject {
    private Map<String, Object> values;
    private String mapname;

    private GameMap() {
    }
    public static GameMap getEmptyMap(String mapname, int height, int width) {
        GameMap gameMap = new GameMap();
        gameMap.mapname = mapname;
        gameMap.values.put("height", height);
        gameMap.values.put("width", width);
        return gameMap;
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


    public Cities getCity() {
        return (Cities) values.get("cities");
    }

    public Heroes getHeroes() {
        return (Heroes) values.get("heroes");
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
    public void add(String name, Object obj) {
        values.put(name, obj);
    }


    @Override
    public boolean check(MyXMLNode node) {
        return true;
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }

    @Override
    public void afterBuilding() {

    }
}
