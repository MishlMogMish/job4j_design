package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final TreeMap<FileProperty, List<Path>> duplicatesMap = new TreeMap<>(
            Comparator.comparing(FileProperty::name).thenComparing(FileProperty::size));

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());

        duplicatesMap.computeIfAbsent(fileProp, fileProperty -> new ArrayList<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        duplicatesMap.values()
                .stream()
                .filter(paths -> paths.size() > 1)
                .forEach(paths -> {
                    paths.forEach(System.out::println);
                });
    }
}
