package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int counterIn = 0;
    private int counterOut = 0;

    public T poll() {
        if (counterIn == 0 && counterOut == 0) {
            throw new NoSuchElementException();
        }
        if (counterOut == 0) {
            while (counterIn > 0) {
                out.push(in.pop());
                counterIn--;
                counterOut++;
            }
        }
        counterOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        counterIn++;
    }
}
