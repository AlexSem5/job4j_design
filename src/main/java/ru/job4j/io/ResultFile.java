package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int size = 5;
            int[][] matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = (i + 1) * (j + 1);
                    out.write((matrix[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
