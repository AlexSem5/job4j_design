package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Класс описывает работу программы, которая читает данные из CSV файла и выводит их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter,
 * приемник данных out и фильтр по столбцам filter.
 * Ключ -out имеет только два допустимых значения: stdout или путь к файлу, куда нужно вывести.
 * <p>
 * Например, если есть файл CSV со столбцами name, age, birthDate, education, children и
 * программа запускается таким образом:
 * -path=file.csv -delimiter=;  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.csv и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 */
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File source = new File(argsName.get("path"));
        List<String> list = new ArrayList<>();
        var ls = System.lineSeparator();
        try (var scanner = new Scanner(new BufferedReader(
                new FileReader(source))).useDelimiter(ls)) {
            String firstLine = scanner.nextLine();
            String filter = argsName.get("filter");
            String[] filters = filter.split(",");
            String delimiter = argsName.get("delimiter");
            String[] strings = firstLine.split(delimiter);
            /**
             *
             */
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                /**
                 *
                 */
            }
            String out = argsName.get("out");
            if ("stdout".equals(out)) {
                System.out.println(list);
            }
            if (!"stdout".equals(out)) {
                try (PrintWriter printer = new PrintWriter(new BufferedWriter(
                        new FileWriter(out)))) {
                    list.forEach(printer::println);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

