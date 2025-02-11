package ru.job4j.serialization.json;

public class Documents {
    private final String passport;
    private final String name;
    private final String visa;
    private final int age;

    public Documents(String passport, String name, String visa, int age) {
        this.passport = passport;
        this.name = name;
        this.visa = visa;
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Documents{");
        sb.append("passport='").append(passport).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", visa='").append(visa).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
