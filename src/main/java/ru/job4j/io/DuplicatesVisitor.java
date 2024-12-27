package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> duplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (attrs.isRegularFile()) {
            FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());

            duplicatesMap.computeIfAbsent(fileProp, fileProperty -> new ArrayList<>()).add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        duplicatesMap.entrySet()
                .stream()
                .filter(propertyListEntry -> propertyListEntry.getValue().size() > 1)
                .sorted(Comparator
                        .comparing(propertyListEntry -> propertyListEntry.getKey().name()))
                .forEach(propertyListEntry -> propertyListEntry
                        .getValue()
                        .stream()
                        .sorted()
                        .forEach(System.out::println));
    }
}
