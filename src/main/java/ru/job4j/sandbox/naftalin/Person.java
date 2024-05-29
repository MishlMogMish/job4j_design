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
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}


