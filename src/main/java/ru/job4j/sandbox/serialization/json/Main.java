package ru.job4j.sandbox.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"});

        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        String jsonString = gson.toJson(person);
        Person person1 = gson.fromJson(jsonString, Person.class);
        System.out.println(person1.age);
        System.out.println(jsonString);

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";

        final Gson gson1 = new Gson();
        final Person personMod = gson1.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}

