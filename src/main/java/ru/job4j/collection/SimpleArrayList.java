package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        newSize();
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T replaced = container[index];
        container[index] = newValue;
        return replaced;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void newSize() {
        if (container.length == size) {
            T[] newArray = (T[]) new Object[container.length * 2];
            System.arraycopy(container, 0, newArray, 0, size);
            container = newArray;
        }
        if (container.length == 0) {
            T[] newArray = (T[]) new Object[2];
            container = newArray;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}
