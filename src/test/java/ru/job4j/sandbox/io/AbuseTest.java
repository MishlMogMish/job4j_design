package ru.job4j.sandbox.io;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AbuseTest {

    @Test
    void drop(@TempDir Path tempDir) throws IOException, InterruptedException {
        File source = tempDir.resolve("source.txt").toFile();
        System.out.println("Temporary directory: " + tempDir.toAbsolutePath());

        try (PrintWriter output = new PrintWriter(source)) {
            output.println("hello foolish dude");
            output.println("java job4j php");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));
        Thread.sleep(60000);
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("hello dude java job4j ").hasToString(result.toString());
    }

    @Test
    void drop1(@TempDir Path tempDir) throws  InterruptedException {
        System.out.println("Temporary directory: " + tempDir.toAbsolutePath());
        Thread.sleep(60000);
        System.out.println("Temporary directory: " + tempDir.toAbsolutePath());
    }

}