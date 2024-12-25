package ru.job4j.sandbox.io;

abstract class Tree<E> {
    public interface Visitor<E, R> {

        R leaf(E elt);

        R branch(R left, R right);
    }

    public abstract <R> R visit(Visitor<E, R> v);

    public static <T> Tree<T> leaf(final T e) {
        return new Tree<>() {
            public <R> R visit(Visitor<T, R> v) {
                return v.leaf(e);
            }
        };
    }

    public static <T> Tree<T> branch(final Tree<T> l, final Tree<T> r) {
        return new Tree<>() {
            public <R> R visit(Visitor<T, R> v) {
                return v.branch(l.visit(v), r.visit(v));
            }
        };
    }
}
