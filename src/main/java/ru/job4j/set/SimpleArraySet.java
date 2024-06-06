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
        boolean isContained = set.size() != 0;
        if (isContained) {
            isContained = false;
            for (T t : set) {
                if (Objects.equals(t, value)) {
                    isContained = true;
                    break;
                }
            }
        }
        return isContained;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
