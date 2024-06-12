package ru.job4j.sandbox.map;

public class DoWhileCheck {
    public static void main(String[] args) {
        int index = 0;
        Integer next = null;
        Integer[] t = new Integer[]{null, null, null, null, null};

        do {
            System.out.println("iteration doWhile) index = " + index);
        } while (index < t.length && (next = t[index++]) == null);

        System.out.println(next);
        System.out.println(index);
        index = 0;

        while (index < t.length && (next = t[index++]) == null) {

            System.out.println("iteration while) index = " + index);
        }
        System.out.println(next);
        System.out.println(index);
    }
}
