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
        while (row < data.length - 1 && data[row].length == 0) {
            row++;
        }
        return row <= data.length - 1 && column <= data[row].length - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length - 1) {
            return data[row][column++];
        } else {
            int rsl = data[row++][column];
            column = 0;
            return rsl;
        }
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
