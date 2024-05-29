package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        /*  удалена строка  generics.printBoundedWildCard(first); т.к. first имеет тип List<Animal>,
            а List<Animal> не является подтипом List<? extends Predator>
            */
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        /*  удалена строк  generics.printLowerBoundedWildCard(third); т.к. third имеет тип List<Tiger>,
            а List<Tiger> не является подтипом List<? super Predator>
            */
        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
    }

    /*Заменяем в параметре метода и итераторе тип List<Object> на List<?>
        - тогда параметр может быть или типа List<Animal> , или типа List<Predator> или типа List<Tiger>
        и из этого  списка можно брать данные. Object в параметре не подходит
        - List<Animal>, List<Tiger, List<Predator> не являются подтипом  List<Object>,
        но являются подтипом List<?> , который эквивалентен List<? Object> ,
        строка Object next = iterator.next(); остается как есть,
        т.к. Tiger, Animal, Predator являются подтипами Object */
    public void printObject(List<?> list) {
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*Заменяем в параметре метода и итераторе тип List<Predator> на List<? extends Predator>
        - тогда параметр может быть  или типа List<Predator> или типа List<Tiger>, но не List<Animal>
        и из этого  списка можно брать данные, что и делает  iterator.next (Object next = iterator.next()).
        List<Predator> в параметре не подходит т.к. List<Tiger, List<Predator> не являются подтипом  List<Object>,
        но являются подтипом List<? extends > , который эквивалентен List<? Object>.
        Cтрока Object next = iterator.next(); остается как есть,
        т.к. Tiger, Animal, Predator являются подтипами Object */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*Заменяем в параметре метода и итераторе тип List<Predator> на List<? super Predator>
        - тогда параметр может быть  или типа List<Predator> или типа List<Animal>, но не List<Tiger>
        и из этого  списка можно брать данные только типа Object,
        что и делает  iterator.next (Object next = iterator.next()).
        List<Predator> в параметре не подходит т.к. List<Tiger, List<Predator> не являются подтипом  List<Object>,
        но являются подтипом List<? extends > , который эквивалентен List<? Object>.
        Cтрока Object next = iterator.next(); остается как есть,
        т.к. Tiger, Animal, Predator являются подтипами Object */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}