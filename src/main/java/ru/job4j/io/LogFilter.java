package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = new ArrayList<>();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] lines = line.split(" ");
                if (lines[lines.length - 2].equals("404")) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
