package ru.job4j.sandbox.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");

            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);

            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            randomAccess.seek(0);
            randomAccess.writeInt(200);
            randomAccess.seek(0);

            System.out.println(randomAccess.readInt());
            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

