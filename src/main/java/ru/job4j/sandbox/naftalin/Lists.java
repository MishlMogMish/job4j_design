package ru.job4j.sandbox.naftalin;

import java.util.*;

public class Lists {
    public static <T> List<T> toList(T... arr) {
        List<T> list = new ArrayList<T>();
        for (T elt : arr) {
            list.add(elt);
        }
        return list;
    }

    public static <T> void addAll(List<T> list, T... arr) {
        for (T elt : arr) {
            list.add(elt);
        }
    }

    public static <T> void copy(List<? super T> dst, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dst.set(i, src.get(i));
        }
    }

    public static double sum(Collection<? extends Number> nums) {
        double s = 0.0;
        for (Number num : nums) {
            s += num.doubleValue();
        }
        return s;
    }

    public static void count(Collection<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++) {
            ints.add(i);
        }
    }

    public static void reverse(List<?> list) {
        Lists.rvs(list);
    }

    private static <T> void rvs(List<T> list) {
        List<T> tmpT = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmpT.get(list.size() - i - 1));
        }
    }

    private static void printResult(Collection<?> collection) {
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    public static void printInfo(Collection<? extends Person> collection) {
        for (Iterator<? extends Person> iterator = collection.iterator(); iterator.hasNext();) {
            Person next = iterator.next();
            System.out.println(next);
        }
    }

    public static void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object line : list) {
            System.out.println("Текущий элемент: " + line);
        }
    }

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(new Person("name", 21, new Date(913716000000L)));
        System.out.println("Количество элементов в списке: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i).toString();
            System.out.println("Текущий элемент: " + line);

        }

        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        printResult(list1);

        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913718000000L)));
        printInfo(pro);

        List<? super Integer> list2 = new ArrayList<>(List.of("str1", "str2"));
        List<? super Integer> list3 = new ArrayList<>(List.of(7.9, "str3"));
        addAll(list2);
        System.out.println();
        addAll(list3);
    }
}
