package ru.job4j.io;

import java.io.File;
import java.util.stream.Stream;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_design\\data");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Stream.of(file.listFiles())
                .forEach(file1 ->
                        System.out.printf("file name: %s, file size: %s%n", file1.getAbsoluteFile(), file1.length()));
    }
}
