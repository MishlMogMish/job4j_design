package ru.job4j.sandbox.io;

import java.io.*;

public class OutputText {

    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/outputfile.txt")) {
            output.write("Hello, world!".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("Hello, world more!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

