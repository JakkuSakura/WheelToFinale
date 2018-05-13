package shared.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Heroes{
    private final List<Hero> heroes = new ArrayList<>();

    public List<Hero> getHeroes() {
        return heroes;
    }

    public int size() {
        return heroes.size();
    }

    public boolean isEmpty() {
        return heroes.isEmpty();
    }

    public boolean contains(Hero hero) {
        return heroes.contains(hero);
    }

    public void clear() {
        heroes.clear();
    }

    public boolean add(Hero hero) {
        return heroes.add(hero);
    }

    public Hero get(int index) {
        return heroes.get(index);
    }

    public Hero set(int index, Hero hero) {
        return heroes.set(index, hero);
    }

    public void add(int index, Hero hero) {
        heroes.add(index, hero);
    }

    public Iterator<Hero> iterator() {
        return heroes.iterator();
    }

}
