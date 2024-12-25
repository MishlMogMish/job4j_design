package ru.job4j.sandbox.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles implements FileVisitor<Path> {
    List<String> list = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path directory,
                                             BasicFileAttributes attributes) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (file.toString().endsWith(".js")) {
            System.out.println(file.toAbsolutePath());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exception) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path directory,
                                              IOException exception) throws IOException {
        return CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }
}