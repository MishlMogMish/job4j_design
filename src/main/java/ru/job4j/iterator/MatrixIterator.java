package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column == data[row].length && row < data.length - 1) {
            column = 0;
            row++;
        }
        while (data[row].length == 0 && row < data.length - 1) {
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    public static void main(String[] args) {
        int[][] data = {
                {},
                {},
                {1, 2, 3},
                {},
                {4, 5},
                {},
                {},
                {6},
                {7, 8, 9, 10},
                {11}
        };

        Iterator<Integer> it = new MatrixIterator(data);

        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
