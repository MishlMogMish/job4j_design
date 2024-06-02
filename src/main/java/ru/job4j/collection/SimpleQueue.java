package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int inCounter;
    int outCounter;

    public T poll() {
        if (inCounter == 0 && outCounter == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outCounter == 0) {
            int bulk = inCounter;
            for (int i = 0; i < bulk; i++) {
                output.push(input.pop());
                inCounter--;
                outCounter++;
            }
        }
        outCounter--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inCounter++;
    }
}
