package ru.job4j.sandbox.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class AttributesUsage {
    public static void main(String[] args) throws IOException {
        System.out.println(File.separator);
        Path file = Path.of("data\\Attributes.txt");
        Files.createFile(file);
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println(attributes.getClass());
        System.out.println("Это обычный файл? " + attributes.isRegularFile());
        System.out.println("Это директория? " + attributes.isDirectory());
        System.out.println("Это символическая ссылка? " + attributes.isSymbolicLink());
        System.out.println("Это не файл, директория или символическая ссылка? " + attributes.isOther());
        System.out.println("Дата создания файла: " + attributes.creationTime());
        System.out.println("Размер файла: " + attributes.size());
        System.out.println("Время последнего доступа: " + attributes.lastAccessTime());
        System.out.println("Время последнего изменения: " + attributes.lastModifiedTime());
        Files.delete(file);
    }
}

