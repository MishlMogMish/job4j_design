package ru.job4j.sandbox.naftalin;

import java.util.Date;

public class Person {

    String name;
    int age;
    Date date;

    public Person(String name, int age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", date=" + date
                + '}';
    }
}

