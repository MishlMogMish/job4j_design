package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        String string;
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            while ((string = input.readLine()) != null) {
                String[] a = string.split(" ");

                if (a[a.length - 2].equals("404")) {
                    list.add(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
