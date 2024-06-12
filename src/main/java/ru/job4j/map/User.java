package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static String binary(int number) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            buffer.append(number % 2 == 0 ? 0 : 1);
            buffer.append((i + 1) % 8 == 0 ? " " : "");
            number /= 2;
        }
        return buffer.reverse().toString();
    }

    public static void main(String[] args) {
        User user1 = new User("Bike", 3, new GregorianCalendar(1989, Calendar.AUGUST, 27));
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 63;

        User user2 = new User("Bike", 3, new GregorianCalendar(1989, Calendar.AUGUST, 27));
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 63;

        Map<User, Object> map = new HashMap<>(64);
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.printf("""
                        User1 хешкод: %s  %s
                                 хеш: %s  %s
                                     коробка: %s  %s
                        """, hashCode1, binary(hashCode1),
                hash1, binary(hash1), bucket1, binary(bucket1));
        System.out.println();
        System.out.printf("""
                        User2 хешкод: %s  %s
                                 хеш: %s  %s
                                     коробка: %s  %s
                        """, hashCode2, binary(hashCode2),
                hash2, binary(hash2), bucket2, binary(bucket2));

        map.values()
                .stream()
                .forEach(System.out::println);
        System.out.println(map.size() + " " + user1.hashCode() + " " + user2.hashCode());

        map.keySet().stream().forEach(System.out::println);
    }
}
