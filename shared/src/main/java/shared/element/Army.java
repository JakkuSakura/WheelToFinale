package shared.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Army {
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


}
