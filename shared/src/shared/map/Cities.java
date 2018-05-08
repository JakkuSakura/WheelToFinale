package shared.map;

import shared.tools.MyXMLNode;
import shared.tree.like.object.TreeLikeObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Cities implements TreeLikeObject {

    private final List<City> arrayList = new ArrayList<>();

    public Iterator<City> iterator() {
        return arrayList.iterator();
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public boolean contains(City city) {
        return arrayList.contains(city);
    }

    public boolean add(City city) {
        return arrayList.add(city);
    }

    public void clear() {
        arrayList.clear();
    }

    @Override
    public void add(String name, Object obj) {
        add((City) obj);
    }

    @Override
    public boolean check(MyXMLNode node) {
        return Objects.equals(node.getXMLType(), "City");
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }

    @Override
    public void afterBuilding() {

    }
}


