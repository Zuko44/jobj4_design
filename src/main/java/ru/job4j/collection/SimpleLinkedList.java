package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> target = head;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> count = head;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return count != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = count.item;
                count = count.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
