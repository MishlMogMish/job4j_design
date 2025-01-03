package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "No root folder provided or no file extension as a command-line arguments.");
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path root = Path.of(args[0]);
        String fileExtension = args[1];
        search(root, path -> path.toString().endsWith(fileExtension))
                .forEach(System.out::println);
    }
}
