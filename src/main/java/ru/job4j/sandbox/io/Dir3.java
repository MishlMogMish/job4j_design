package ru.job4j.sandbox.io;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Dir3 {
    private long calculateDirectorySize(File directory, Map<File, Long> filesAndSpace) {
        long size = 0;
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                size += file.length();
                filesAndSpace.put(file, file.length());
            } else {
                long subDirectorySize = calculateDirectorySize(file, filesAndSpace);
                filesAndSpace.put(file, subDirectorySize);
                size += subDirectorySize;
            }
        }
        return size;
    }

    private void printFiles(Map<File, Long> filesAndSpace, long totalDirectorySize, String directoryName) {
        for (File file : filesAndSpace.keySet()) {
            if (file.isFile()) {
                System.out.printf("       %-15d   %15s \n", filesAndSpace.get(file), file.getAbsolutePath());
            } else {
                System.out.printf("\n       %-12d Dir  %15s \n", filesAndSpace.get(file), file.getAbsolutePath());
            }

        }
        System.out.printf("\n%-20s %-10s   %,14d\n%-27s         %,10d байт", "Количество файлов в",
                directoryName,  filesAndSpace.size(), "Размер директории", totalDirectorySize);
    }

    public void processDirectory(File sourceDirectory) {
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Директория не существует или это файл: %s",
                            sourceDirectory.getAbsoluteFile()));
        }

        Map<File, Long> filesWithSizes = new TreeMap<>();

        long totalDirectorySize = calculateDirectorySize(sourceDirectory, filesWithSizes);
        printFiles(filesWithSizes, totalDirectorySize, sourceDirectory.getAbsolutePath());
    }

    public static void main(String[] args) {
        Dir3 dir1 = new Dir3();
        dir1.processDirectory(new File("C:\\Projects"));
    }
}
