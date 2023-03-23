package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean total = contains(value);
        if (!total) {
            set.add(value);
        }
        return !total;
    }

    @Override
    public boolean contains(T value) {
        boolean flag = false;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(set.get(i), value)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
