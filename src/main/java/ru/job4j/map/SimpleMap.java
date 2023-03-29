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
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        final int i = indexFor(key);
        final boolean rsl = table[i] == null;
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

    private int indexFor(K key) {
        return (table.length - 1) & hash(key == null ? 0 : key.hashCode());
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity <<= 1;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                final int i = indexFor(entry.key);
                table[i] = new MapEntry<>(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        final MapEntry<K, V> entry = table[indexFor(key)];
        V value = null;
        if (entry != null) {
            value = keysEquals(key, entry.key) ? entry.value : null;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        final int i = indexFor(key);
        final boolean rsl = table[i] != null && keysEquals(key, table[i].key);
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

    private boolean keysEquals(K key1, K key2) {
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
