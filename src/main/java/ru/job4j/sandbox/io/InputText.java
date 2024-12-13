package ru.job4j.sandbox.io;

import java.io.FileInputStream;
import java.io.IOException;

public class InputText {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            StringBuilder text2 = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
                text.append(" ");

                text2.append(read);
                text2.append(" ");
                System.out.println(text);
                System.out.println(text2);
            }
            System.out.println(text);
            System.out.println(text2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


