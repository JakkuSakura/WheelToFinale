package shared.map;

import shared.Tools.XMLTool.MyXMLNode;
import shared.Tools.XMLTool.MyXMLType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Army implements MyXMLType {
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
    public Object get() {
        return this;
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
}
