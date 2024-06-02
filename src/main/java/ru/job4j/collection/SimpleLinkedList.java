package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {
    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> tail = head;
            for (int i = 0; i < size - 1; i++) {
                tail = tail.next;
            }
            tail.next = new Node<E>(value, null);
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> indexNode = head;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.next;
        }
        return indexNode.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                    node = node.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        Node<E> next;
        E value;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
