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

                if ("404".equals(a[a.length - 2])) {
                    list.add(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {

        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)))) {
            data.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
