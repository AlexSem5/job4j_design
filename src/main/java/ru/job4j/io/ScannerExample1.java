package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) {
//        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
//        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
//                .useDelimiter(", ");
//        while (scanner.hasNext()) {
//            System.out.println(scanner.next());
//        }
        var ls = System.lineSeparator();
        try (var scanner = new Scanner(new BufferedReader(
                new FileReader("log.txt")))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
