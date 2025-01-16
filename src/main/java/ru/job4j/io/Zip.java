package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private void writeToZip(Path source, ZipOutputStream zos, String entryName) throws IOException {
        zos.putNextEntry(new ZipEntry(entryName));

        try (BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(source.toFile()))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Error while packing file: " + source, e);
        }
        zos.closeEntry();
    }

    private ArgsName argsValidate(String[] args) {
        ArgsName names = ArgsName.of(args);
        String directory = names.get("d");
        if (directory == null) {
            throw new IllegalArgumentException("Source directory is missing");
        }
        if (!directory.startsWith(".")) {
            throw new IllegalArgumentException("Source directory doesn't start with '.' " + directory);
        }

        String excluded = names.get("e");
        if (excluded == null) {
            throw new IllegalArgumentException("Excluded files argument is missing");
        }
        if (!excluded.startsWith(".")) {
            throw new IllegalArgumentException("Excluded files argument doesn't start with '.' " + excluded);
        }

        String output = names.get("o");
        if (output == null) {
            throw new IllegalArgumentException("Output is missing");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Output doesn't end with '.zip' " + output);
        }
        return names;
    }

    public void packFiles(List<Path> sources, File target) throws IOException {
        if (target.exists()) {
            Files.delete(target.toPath());
        }
        try (ZipOutputStream zos = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {

            for (Path source : sources) {
                if (Files.isSameFile(source, target.toPath())) {
                    continue;
                }

                String entryName = Paths.get(".")
                        .relativize(source).toString().replace("\\", "/");
                writeToZip(source, zos, entryName);
            }
        } catch (IOException e) {
            throw new IOException("Error while packing files " + target, e);
        }
    }

    public void packSingleFile(File source, File target) throws IOException {
        if (!source.exists() || !source.isFile()) {
            throw  new IllegalArgumentException("Source file doesn't exist or isn't a file" + source);
        }

        try (ZipOutputStream zos = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            String normalizedPath = source.getPath().replaceFirst("^\\.(/|\\\\)", "");
            writeToZip(source.toPath(), zos, normalizedPath);
        } catch (Exception e) {
            throw new IOException("Error while packing files " + source, e);
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName argsName = zip.argsValidate(args);
        List<Path> sources = Search.search(Path.of(argsName.get("d")),
                path -> !path.toString().endsWith(argsName.get("e")));

        zip.packFiles(sources, new File(argsName.get("o")));
    }
}

