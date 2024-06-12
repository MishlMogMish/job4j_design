package ru.job4j.sandbox.map.reflaction;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {
    /*    This is to simulate instances which are not equal but go to the same bucket.*/
    static class A {
        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            return 42;
        }
    }

    public static void main(String[] args) {
        /*Test data*/
        HashMap<A, String> map = new HashMap<A, String>(4);
        map.put(new A(), "abc");
        map.put(new A(), "def");

        /*Access to the internal table*/
        Class clazz = map.getClass();
        Field table = null;
        try {
            table = clazz.getDeclaredField("table");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        table.setAccessible(true);
        Map.Entry<Integer, String>[] realTable = new Map.Entry[0];
        try {
            realTable = (Map.Entry<Integer, String>[]) table.get(map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        /*Iterate and do pretty printing*/
        for (int i = 0; i < realTable.length; i++) {
            try {
                System.out.println(String.format("Bucket : %d, Entry: %s", i, bucketToString(realTable[i])));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String bucketToString(Map.Entry<Integer, String> entry) throws Exception {
        if (entry == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        /*Access to the "next" filed of HashMap$Node*/
        Class clazz = entry.getClass();
        Field next = clazz.getDeclaredField("next");
        next.setAccessible(true);

        /*going through the bucket*/
        while (entry != null) {
            sb.append(entry);
            entry = (Map.Entry<Integer, String>) next.get(entry);
            if (null != entry) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}