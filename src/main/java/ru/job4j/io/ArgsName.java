package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String string : args) {
            String[] keyValue = string.split("=", 2);
            values.put(keyValue[0].substring(1), keyValue[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments provided to the program.");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("This argument '" + arg + "' doesn't start with a '-'");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Argument '" + arg + "' doesn't contain '='");
            }

            String[] keyValue = arg.split("=", 2);

            if (keyValue[0].substring(1).isEmpty()) {
                throw new IllegalArgumentException("Argument '" + arg + "' doesn't contain a key.");
            }
            if (keyValue[1].isEmpty()) {
                throw new IllegalArgumentException("Argument '" + arg + "' is missing a value.");
            }
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{
                "-Xmx=512", "-encoding=UTF-8"});

        ArgsName zip = ArgsName.of(new String[]{
                "-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
