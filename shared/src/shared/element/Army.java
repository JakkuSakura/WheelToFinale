package shared.element;

import shared.tools.MyXMLNode;
import shared.tree.like.object.TreeLikeObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Army implements TreeLikeObject {
    private final List<Soldier> soldierList = new ArrayList<>();

    public List<Soldier> getSoldierList() {
        return soldierList;
    }

    public int size() {
        return soldierList.size();
    }

    public boolean isEmpty() {
        return soldierList.isEmpty();
    }

    public void clear() {
        soldierList.clear();
    }

    public Soldier get(int index) {
        return soldierList.get(index);
    }

    public Soldier set(int index, Soldier soldier) {
        return soldierList.set(index, soldier);
    }

    public void add(Soldier soldier) {
        soldierList.add(soldier);
    }

    public Iterator<Soldier> iterator() {
        return soldierList.iterator();
    }

    @Override
    public void add(String name, Object obj) {
        add((Soldier) obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return Objects.equals(node.getXMLType(), "Soldier");
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }

    @Override
    public void afterBuilding() {

    }
}
