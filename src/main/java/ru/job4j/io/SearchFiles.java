package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles {

    public  static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        List<Path> paths = new ArrayList<>();
        Files.walkFileTree(root, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (condition.test(file)) {
                    paths.add(file);
                }
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.err.println("fail to visit file " + file + " - " + exc.getMessage());
                return CONTINUE;
            }
        });
        return paths;
    }

    public static void main(String[] args) throws IOException {
        search(Paths.get("."), path -> path.toString().endsWith(".js"))
                .forEach(System.out::println);
    }
}
