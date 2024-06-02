package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private ForwardLinked.Node<T> head;

    public void add(T value) {
        head = new ForwardLinked.Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> indexNode = head;
        for (int i = 0; i < size - index - 1; i++) {
            indexNode = indexNode.next;
        }
        return indexNode.value;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> last = head;
        for (int i = 0; i < size - 1; i++) {
            last = last.next;
        }
        T value = last.value;
        last = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;
            int expectedModCount = modCount;
            ForwardLinked.Node<T> node = inverseLinkedList(head);

            ForwardLinked.Node<T> inverseLinkedList(ForwardLinked.Node<T> head) {
                ForwardLinked.Node<T> nextNode = head;
                ForwardLinked.Node<T> beforeNode = null;
                for (int i = 0; i < size; i++) {
                    beforeNode = new ForwardLinked.Node<>(nextNode.value, beforeNode);
                    nextNode = nextNode.next;
                }
                return beforeNode;
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return counter < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = node.value;
                if (counter != size - 1) {
                    node = node.next;
                }
                counter++;
                return value;
            }
        };
    }

    private static class Node<T> {
        ForwardLinked.Node<T> next;
        T value;

        public Node(T value, ForwardLinked.Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
