package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonMain {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(true, 3500, new String[]{"MOW", "DEL"}, new Documents(
                "123445 2344", "Oleg Makarov", "2345-VN", 35));
        System.out.println("Ticket : " + ticket);

        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String gsonString = gson.toJson(ticket);
        System.out.println("\n Serialized and deserialized by Json: " + gsonString);
    }
}
