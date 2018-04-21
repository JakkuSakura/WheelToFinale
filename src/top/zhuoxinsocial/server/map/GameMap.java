package top.zhuoxinsocial.server.map;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import top.zhuoxinsocial.MyXMLNode;
import top.zhuoxinsocial.XMLTools;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GameMap {
    private Map<String, Object> values = new HashMap<>();
    private String mapname;
    private Citys citys = new Citys();
    private Heroes heroes = new Heroes();

    public GameMap(String mapname) {
        //        Try to load a map after search the path of map
    }


    public void loadMap(String filename) throws IOException, SAXException, ParserConfigurationException {
        XMLTools xmlTools = new XMLTools();
        MyXMLNode xmlNode = xmlTools.readXML(filename);
        xmlNode.printTree();
        MyXMLNode map = Objects.requireNonNull(xmlNode.getNodeByPath("map"), "Key not exits");
        XMLTools.putIntoMap(map, "width", values, null, node -> node.getInt(0));
        XMLTools.putIntoMap(map, "width", values, null, node -> node.getInt(0));

        MyXMLNode heroes = Objects.requireNonNull(map.getChild("heroes"), "Cannot find 'heroes'");


        heroes.forEachNode(Node.ELEMENT_NODE, (index, node) -> {
            if (node.hasText())
                return;
            Hero hero = new Hero();
            hero.setName(Objects.requireNonNull(node.getChild("name"), "Cannot find 'name'")
                    .getText("No_name"));
            hero.setLife(Objects.requireNonNull(node.getChild("life"), "Cannot find 'life'")
                    .getInt(0));
            Army army = new Army();
            Optional.ofNullable(node.getChild("army")).ifPresent(
                    s -> s.forEachNode(Node.ELEMENT_NODE, (index1, node1) ->
                            army.add(new Soldier()
                                    .set("name", XMLTools.getValue(node1, "name", null,
                                            n -> n.getText("No_name")))
                                    .set("health", XMLTools.getValue(node1, "name", null,
                                            n -> n.getInt(0)))
                                    .set("power", XMLTools.getValue(node1, "power", null,
                                            n -> n.getInt(0)))
                                    .set("defence", XMLTools.getValue(node1, "defence", null,
                                            n -> n.getInt(0)))

                            )
                    )
            );

            hero.setArmy(army);
            this.heroes.add(hero);

        });
        MyXMLNode cities = Objects.requireNonNull(map.getChild("cities"), "Cannot find 'cities'");
        cities.forEachNode(Node.ELEMENT_NODE, (index, node) -> Optional.ofNullable(node.getChild("army")).ifPresent(
                s -> s.forEachNode(Node.ELEMENT_NODE, (index1, node1) ->
                        citys.add(new City()
                                .set("name", Objects.requireNonNull(node1.getChild("name"), "Cannot find 'name'")
                                        .getText("No_name"))
                                .set("health", Objects.requireNonNull(node1.getChild("health"), "Cannot find 'health'")
                                        .getInt(0))
                                .set("power", Objects.requireNonNull(node1.getChild("power"), "Cannot find 'power'")
                                        .getInt(0))
                                .set("defence", Objects.requireNonNull(node1.getChild("defence"), "Cannot find 'defence'")
                                        .getInt(0))
                        )
                )
        ));
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        GameMap gameMap = new GameMap("test");
        gameMap.loadMap("maps/demo.xml");
    }
}
