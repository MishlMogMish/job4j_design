package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;
        modCount = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        oldTable = null;
    }

    public int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    public int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    @Override
    public V get(K key) {
        return isExist(key) ? table[indexFor(hash(Objects.hashCode(key)))].value : null;
    }

    private boolean isExist(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> el = table[index];
        return el != null && Objects.hashCode(key) == Objects.hashCode(el.key)
                && Objects.equals(key, el.key);
    }

    @Override
    public boolean remove(K key) {
        boolean isRemoved = false;
        if (isExist(key)) {
            table[indexFor(hash(Objects.hashCode(key)))] = null;
            count--;
            modCount--;
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index = 0;
            int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && Objects.equals(table[index], null)) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index <= count && Objects.equals(table[index], null)) {
                    index++;
                }

                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));

        map.put(1, "01");
        map.put(2, "02");
        map.put(3, "03");
        map.put(4, "04");
        map.put(null, "null");
        map.put(15, "15");

        printMap(map);
        System.out.println("count: " + map.count);
        System.out.println();

        System.out.println(map.put(8, "08"));

        printMap(map);
        System.out.println("count: " + map.count);
        System.out.println("capacity: " + map.capacity);
    }

    private static void printMap(NonCollisionMap<Integer, String> map) {
        int i = 0;

        for (MapEntry<Integer, String> entry : map.table) {
            if (entry == null) {
                System.out.printf("bucket = %d key = %s, value = %s\n", i++, null, null);
            } else {
                System.out.printf("bucket = %d  key = %d, value = %s\n", i++, entry.key, entry.value);

            }
        }
    }
}
