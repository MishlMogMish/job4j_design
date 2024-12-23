package ru.job4j.sandbox.io;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class Dir2 {
    private class DirNode {
        File directory;
        long size;
        List<File> files;

        private DirNode(File directory) {
            this.directory = directory;
            fileOrderingConstruct();
        }

        private void filesValidation() {
            File[] aFiles = directory.listFiles();
            if (aFiles == null) {
                System.err.printf("Can't get directory files " + directory.getAbsolutePath());
                files = Arrays.stream(aFiles).toList();
            }

        }

        private void fileOrderingConstruct() {
            filesValidation();
            files.sort((f1, f2) -> (f1.getName().compareTo(f2.getName())));
        }

        private long sizeConstruct() {
            for (File file : files) {
                size += file.isFile() ? file.length() : new DirNode(file).size;
            }
            return size;
        }

        private void printDir() {
            System.out.printf("s" + 4 + "%Dir %s, size %d, path %s", directory.getName(), size);
        }
    }

    public void printDirectoriesAndTheirFilesWithSize(File directory) {
        if (!directory.exists() | !directory.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("There is no such directory, or it's a file: %s",
                            directory.getAbsolutePath()));
        }
        TreeMap<Integer, List<DirNode>> nameSize = new TreeMap<>();

        for (File file : new DirNode(directory).files) {
            if (file.isDirectory()) {
                int level = directory.getAbsolutePath().split(Pattern.quote(File.separator)).length;
                List<File> newFile = new ArrayList<>();
            }
        }

        for (Integer i : nameSize.descendingKeySet()) {
            int in = 0;
        }

    }
}
