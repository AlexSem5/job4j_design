package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream(target)))) {
            String line;
            boolean isServer = true;
            while ((line = read.readLine()) != null) {
                String[] lines = line.split(" ", 2);
                if (isServer && (line.contains("400") || line.contains("500"))) {
                    isServer = false;
                    out.printf("%s;", lines[1]);
                }
                if (!isServer && (line.contains("200") || line.contains("300"))) {
                    isServer = true;
                    out.printf("%s;%s", lines[1], System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/log.txt", "./data/new.txt");
    }
}
