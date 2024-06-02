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
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> indexNode = head;
        for (int i = 0; i < size - index - 1; i++) {
            indexNode = indexNode.next;
        }
        return indexNode.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;
            int expectedModCount = modCount;
            Node<E> node = inverseLinkedList(head);

            Node<E> inverseLinkedList(Node<E> head) {
                Node<E> nextNode = head;
                Node<E> beforeNode = null;
                for (int i = 0; i < size; i++) {
                    beforeNode = new Node<>(nextNode.value, beforeNode);
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
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E value = node.value;
                if (counter != size - 1) {
                    node = node.next;
                }
                counter++;
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
