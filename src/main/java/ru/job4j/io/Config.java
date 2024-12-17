package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader
                    .lines()
                    .filter(s -> !s.trim().isEmpty() && !s.trim().startsWith("#"))
                    .map(s -> s.split("=", 2))
                    .forEach(arr -> {
                                if (arr.length < 2 || arr[0].isEmpty() || arr[1].isEmpty()) {
                                    throw new IllegalArgumentException("Have no '=' or empty key or empty value" + Arrays.toString(arr));
                                }
                                values.put(arr[0].trim(), arr[1].trim());
                            }
                    );
        } catch (IOException e) {
            throw new IllegalStateException("File not found", e);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
