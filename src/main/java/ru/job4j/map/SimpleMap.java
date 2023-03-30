package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
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
        int i = index(key);
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int index(K key) {
        return key != null ? indexFor(hash(key.hashCode())) : 0;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity <<= 1;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                int i = index(entry.key);
                table[i] = entry;
            }
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[index(key)];
        V value = null;
        if (entry != null) {
            value = keyEquals(key, entry.key) ? entry.value : null;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int i = index(key);
        boolean rsl = table[i] != null && keyEquals(key, table[i].key);
        if (rsl) {
            table[i] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expected = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expected != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (count < capacity && table[count] == null) {
                    count++;
                }
                return count < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    private boolean keyEquals(K key1, K key2) {
        boolean total;
        if (key2 != null) {
            total = key1 == key2
                    || key1 != null && key1.hashCode() == key2.hashCode() && key1.equals(key2);
        } else {
            total = key1 == key2;
        }
        return total;
    }

    private static class MapEntry<K, V> {
        private K key;
        private V value;

        private MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
