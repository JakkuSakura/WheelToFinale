package top.zhuoxinsocial.server.map;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import top.zhuoxinsocial.MyXMLNode;
import top.zhuoxinsocial.XMLTools;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameMap {
    private int height;
    private int width;
    private String mapname;
    private Citys citys;
    private Heroes heroes = new Heroes();
    public GameMap(String mapname) {
        this.mapname = mapname;
        citys = new Citys();
//        Try to load a map after search the path of map
    }

    public void loadMap(String filename) throws IOException, SAXException, ParserConfigurationException {
        XMLTools xmlTools = new XMLTools();
        MyXMLNode xmlNode = xmlTools.readXML(filename);
        xmlNode.printTree();
        MyXMLNode map = xmlNode.getNodeByPath("map");
        this.height = map.getChild("height").getInt(0);
        this.width = map.getChild("width").getInt(0);


        MyXMLNode heroes = map.getChild("heroes");
        heroes.forEachNode((index, node) -> {

        });

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        GameMap gameMap = new GameMap("test");
        gameMap.loadMap("maps/demo.xml");
    }
}
