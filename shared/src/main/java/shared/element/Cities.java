package shared.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cities {

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

}


