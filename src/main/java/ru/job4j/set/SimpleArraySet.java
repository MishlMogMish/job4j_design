package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean isAdded = !contains(value);
        if (isAdded) {
            set.add(value);
        }
        return isAdded;
    }

    @Override
    public boolean contains(T value) {
        boolean isContained = false;
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), value)) {
                isContained = true;
                break;
            }
        }
        return isContained;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
