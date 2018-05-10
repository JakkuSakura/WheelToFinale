package shared.element;

import shared.tools.MyXMLNode;
import shared.tree.like.object.TreeLikeObject;
import shared.player.Player;


public class Hero implements TreeLikeObject {
    private String name;
    private int life;
    private Army army;
    private Player owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public void add(String name, Object obj) {
        switch (name) {
            case "name":
                setName((String) obj);
                break;
            case "life":
                setLife((int) obj);
                break;
            case "army":
                setArmy((Army) obj);
                break;
            case "player":
                setOwner((Player) obj);
                break;
            default:
                throw new IllegalArgumentException("Known item " + name);
        }
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
