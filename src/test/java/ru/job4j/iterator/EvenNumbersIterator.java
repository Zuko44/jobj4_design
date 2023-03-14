package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean next = false;
        if (data.length != 0) {
            for (int i = index; i < data.length; i++) {
                if (data[i] % 2 == 0) {
                    index = i;
                    next = true;
                    break;
                }
            }
        }
        return next;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
